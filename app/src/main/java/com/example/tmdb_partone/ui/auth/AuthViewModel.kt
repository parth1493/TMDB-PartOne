package com.example.tmdb_partone.ui.auth

import androidx.lifecycle.LiveData
import com.example.tmdb_partone.models.AuthToken
import com.example.tmdb_partone.repository.auth.AuthRepository
import com.example.tmdb_partone.ui.BaseViewModel
import com.example.tmdb_partone.ui.DataState
import com.example.tmdb_partone.ui.auth.state.AuthStateEvent
import com.example.tmdb_partone.ui.auth.state.AuthViewState
import com.example.tmdb_partone.ui.auth.state.LoginFields
import com.example.tmdb_partone.ui.auth.state.RegistrationFields
import com.example.tmdb_partone.util.AbsentLiveData
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): BaseViewModel<AuthStateEvent, AuthViewState>()
{
    override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
        when(stateEvent){

            is AuthStateEvent.LoginAttemptEvent -> {
                return AbsentLiveData.create()
            }

            is AuthStateEvent.RegisterAttemptEvent -> {
                return AbsentLiveData.create()
            }

            is AuthStateEvent.CheckPreviousAuthEvent -> {
                return AbsentLiveData.create()
            }
        }
    }

    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }

    fun setRegistrationFields(registrationFields: RegistrationFields){
        val update = getCurrentViewStateOrNew()
        if(update.registrationFields == registrationFields){
            return
        }
        update.registrationFields = registrationFields
        _viewState.value = update
    }

    fun setLoginFields(loginFields: LoginFields){
        val update = getCurrentViewStateOrNew()
        if(update.loginFields == loginFields){
            return
        }
        update.loginFields = loginFields
        _viewState.value = update
    }

    fun setAuthToken(authToken: AuthToken){
        val update = getCurrentViewStateOrNew()
        if(update.authToken == authToken){
            return
        }
        update.authToken = authToken
        _viewState.value = update
    }
}
