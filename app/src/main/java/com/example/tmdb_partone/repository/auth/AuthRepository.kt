package com.example.tmdb_partone.repository.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.example.tmdb_partone.api.auth.OpenApiAuthService
import com.example.tmdb_partone.api.auth.network_response.LoginResponse
import com.example.tmdb_partone.api.auth.network_response.RegistrationResponse
import com.example.tmdb_partone.models.AuthToken
import com.example.tmdb_partone.persistence.AccountPropertiesDao
import com.example.tmdb_partone.persistence.AuthTokenDao
import com.example.tmdb_partone.repository.NetworkBoundResource
import com.example.tmdb_partone.session.SessionManager
import com.example.tmdb_partone.ui.DataState
import com.example.tmdb_partone.ui.Response
import com.example.tmdb_partone.ui.ResponseType
import com.example.tmdb_partone.ui.auth.state.AuthViewState
import com.example.tmdb_partone.ui.auth.state.LoginFields
import com.example.tmdb_partone.ui.auth.state.RegistrationFields
import com.example.tmdb_partone.util.ErrorHandling.Companion.ERROR_UNKNOWN
import com.example.tmdb_partone.util.ErrorHandling.Companion.GENERIC_AUTH_ERROR
import com.example.tmdb_partone.util.GenericApiResponse
import kotlinx.coroutines.Job
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
        val authTokenDao: AuthTokenDao,
        val accountPropertiesDao: AccountPropertiesDao,
        val openApiAuthService: OpenApiAuthService,
        val sessionManager: SessionManager
)
{

    private val TAG: String = "AppDebug"

    private var repositoryJob: Job? = null


    fun attemptLogin(email: String,
                     password: String): LiveData<DataState<AuthViewState>>{

        val loginFieldErrors = LoginFields(email, password).isValidForLogin()
        if(!loginFieldErrors.equals(LoginFields.LoginError.none())){
            return returnErrorResponse(loginFieldErrors, ResponseType.Dialog())
        }

        return object: NetworkBoundResource<LoginResponse, AuthViewState>(
                sessionManager.isConnectedToTheInternet()
        ){
            override suspend fun handleApiSuccessResponse(response: GenericApiResponse.ApiSuccessResponse<LoginResponse>) {
                Log.d(TAG, "handleApiSuccessResponse: ${response}")

                // Incorrect login credentials counts as a 200 response from server, so need to handle that
                if(response.body.response.equals(GENERIC_AUTH_ERROR)){
                    return onErrorReturn(response.body.errorMessage, true, false)
                }

                onCompleteJob(
                        DataState.data(
                                data = AuthViewState(
                                        authToken = AuthToken(response.body.pk, response.body.token)
                                )
                        )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<LoginResponse>> {
                return openApiAuthService.login(email, password)
            }

            override fun setJob(job: Job) {
                repositoryJob?.cancel()
                repositoryJob = job
            }

        }.asLiveData()
    }

    fun attemptRegistration(
            email: String,
            username: String,
            password: String,
            confirmPassword: String
    ): LiveData<DataState<AuthViewState>>{

        val registrationFieldErrors = RegistrationFields(email, username, password, confirmPassword).isValidForRegistration()
        if(!registrationFieldErrors.equals(RegistrationFields.RegistrationError.none())){
            return returnErrorResponse(registrationFieldErrors, ResponseType.Dialog())
        }

        return object: NetworkBoundResource<RegistrationResponse, AuthViewState>(
                sessionManager.isConnectedToTheInternet()
        ){
            override suspend fun handleApiSuccessResponse(response: GenericApiResponse.ApiSuccessResponse<RegistrationResponse>) {

                Log.d(TAG, "handleApiSuccessResponse: ${response}")

                if(response.body.response.equals(GENERIC_AUTH_ERROR)){
                    return onErrorReturn(response.body.errorMessage, true, false)
                }

                onCompleteJob(
                        DataState.data(
                                data = AuthViewState(
                                        authToken = AuthToken(response.body.pk, response.body.token)
                                )
                        )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<RegistrationResponse>> {
                return openApiAuthService.register(email, username, password, confirmPassword)
            }

            override fun setJob(job: Job) {
                repositoryJob?.cancel()
                repositoryJob = job
            }

        }.asLiveData()
    }


    private fun returnErrorResponse(errorMessage: String, responseType: ResponseType): LiveData<DataState<AuthViewState>>{
        Log.d(TAG, "returnErrorResponse: ${errorMessage}")

        return object: LiveData<DataState<AuthViewState>>(){
            override fun onActive() {
                super.onActive()
                value = DataState.error(
                        Response(
                                errorMessage,
                                responseType
                        )
                )
            }
        }
    }

    fun cancelActiveJobs(){
        Log.d(TAG, "AuthRepository: Cancelling on-going jobs...")
        repositoryJob?.cancel()
    }



}
