package com.example.tmdb_partone.ui

import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity :DaggerAppCompatActivity() {
    public val TAG: String = "AppDebug"
}