package com.example.tmdb_partone.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb_partone.R
import com.example.tmdb_partone.ui.BaseActivity
import com.example.tmdb_partone.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        Log.d(TAG, "AuthActivity: ${viewModel.hashCode()}")
    }
}
