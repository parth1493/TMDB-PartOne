package com.example.tmdb_partone.di

import androidx.lifecycle.ViewModelProvider
import com.example.tmdb_partone.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}