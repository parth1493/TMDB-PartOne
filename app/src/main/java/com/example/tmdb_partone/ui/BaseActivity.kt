package com.example.tmdb_partone.ui

import com.example.tmdb_partone.session.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity :DaggerAppCompatActivity() {
    public val TAG: String = "AppDebug"

    @Inject
    lateinit var sessionManager: SessionManager
}