package com.example.tmdb_partone.di.auth

import androidx.lifecycle.ViewModel
import com.example.tmdb_partone.di.ViewModelKey
import com.example.tmdb_partone.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}