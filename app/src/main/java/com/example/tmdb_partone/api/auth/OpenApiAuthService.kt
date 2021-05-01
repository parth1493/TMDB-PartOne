package com.example.tmdb_partone.api.auth

import androidx.lifecycle.LiveData
import com.example.tmdb_partone.api.auth.network_response.LoginResponse
import com.example.tmdb_partone.api.auth.network_response.RegistrationResponse
import com.example.tmdb_partone.util.GenericApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OpenApiAuthService {

    @POST("account/login")
    @FormUrlEncoded
    fun login(
        @Field("username") email: String,
        @Field("password") password: String
    ): LiveData<GenericApiResponse<LoginResponse>>

    @POST("account/register")
    @FormUrlEncoded
    fun register(
            @Field("email") email: String,
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("password2") password2: String
    ): LiveData<GenericApiResponse<RegistrationResponse>>

}