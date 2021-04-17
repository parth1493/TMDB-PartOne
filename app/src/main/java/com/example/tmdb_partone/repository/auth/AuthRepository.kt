package com.example.tmdb_partone.repository.auth

import androidx.lifecycle.LiveData
import com.example.tmdb_partone.api.auth.OpenApiAuthService
import com.example.tmdb_partone.api.auth.network_response.LoginResponse
import com.example.tmdb_partone.api.auth.network_response.RegistrationResponse
import com.example.tmdb_partone.persistence.AccountPropertiesDao
import com.example.tmdb_partone.persistence.AuthTokenDao
import com.example.tmdb_partone.session.SessionManager
import com.example.tmdb_partone.util.GenericApiResponse
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
) {
    fun testLoginRequest(email: String, password: String): LiveData<GenericApiResponse<LoginResponse>>{
        return openApiAuthService.login(email, password)
    }

    fun testRegistrationRequest(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<GenericApiResponse<RegistrationResponse>> {
        return openApiAuthService.register(email, username, password, confirmPassword)
    }
}