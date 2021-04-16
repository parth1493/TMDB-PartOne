package com.example.tmdb_partone.session

import android.app.Application
import com.example.tmdb_partone.persistence.AuthTokenDao

class SessionManager constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
){
}