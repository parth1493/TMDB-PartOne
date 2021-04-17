package com.example.tmdb_partone.session

import android.app.Application
import com.example.tmdb_partone.persistence.AuthTokenDao
import javax.inject.Inject

class SessionManager
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
){
}