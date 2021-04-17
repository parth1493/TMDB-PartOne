package com.example.tmdb_partone.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb_partone.api.auth.network_response.LoginResponse
import com.example.tmdb_partone.api.auth.network_response.RegistrationResponse
import com.example.tmdb_partone.repository.auth.AuthRepository
import com.example.tmdb_partone.util.GenericApiResponse
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository,
): ViewModel() {
    fun testLogin(): LiveData<GenericApiResponse<LoginResponse>>{
        return authRepository.testLoginRequest(
            "parth1493@gmail.com",
            "Shlok1493"
        )
    }

    fun testRegister(): LiveData<GenericApiResponse<RegistrationResponse>> {
        return authRepository.testRegistrationRequest(
            "mitchelltabian1234000@gmail.com",
            "mitchelltabian123400",
            "codingwithmitch1",
            "codingwithmitch1"
        )
    }
}