package com.example.tmdb_partone.repository.auth

import com.example.tmdb_partone.api.auth.OpenApiAuthService
import com.example.tmdb_partone.persistence.AccountPropertiesDao
import com.example.tmdb_partone.persistence.AuthTokenDao
import com.example.tmdb_partone.session.SessionManager

class AuthRepository constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
) {
}