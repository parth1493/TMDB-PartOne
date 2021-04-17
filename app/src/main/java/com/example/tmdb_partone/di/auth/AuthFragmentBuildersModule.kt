package com.example.tmdb_partone.di.auth

import com.example.tmdb_partone.ui.auth.ForgotPasswordFragment
import com.example.tmdb_partone.ui.auth.LauncherFragment
import com.example.tmdb_partone.ui.auth.LoginFragment
import com.example.tmdb_partone.ui.auth.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
abstract class AuthFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeLauncherFragment(): LauncherFragment

    @ContributesAndroidInjector()
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector()
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector()
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment

}