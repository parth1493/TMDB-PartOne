package com.example.tmdb_partone.di

import com.example.tmdb_partone.di.auth.AuthFragmentBuildersModule
import com.example.tmdb_partone.di.auth.AuthModule
import com.example.tmdb_partone.di.auth.AuthScope
import com.example.tmdb_partone.di.auth.AuthViewModelModule
import com.example.tmdb_partone.ui.auth.AuthActivity
import com.example.tmdb_partone.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}