package com.example.tmdb_partone.di.auth

import com.example.tmdb_partone.api.auth.OpenApiAuthService
import com.example.tmdb_partone.persistence.AccountPropertiesDao
import com.example.tmdb_partone.persistence.AuthTokenDao
import com.example.tmdb_partone.repository.auth.AuthRepository
import com.example.tmdb_partone.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule{

    @AuthScope
    @Provides
    fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): OpenApiAuthService {
        return retrofitBuilder
            .build()
            .create(OpenApiAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(
        sessionManager: SessionManager,
        authTokenDao: AuthTokenDao,
        accountPropertiesDao: AccountPropertiesDao,
        openApiAuthService: OpenApiAuthService
    ): AuthRepository {
        return AuthRepository(
            authTokenDao,
            accountPropertiesDao,
            openApiAuthService,
            sessionManager
        )
    }

}