package com.example.tmdb_partone.ui.auth.state

import android.content.Context

sealed class AuthStateEvent{

    data class LoginAttemptEvent(
        val email: String,
        val password: String
    ): AuthStateEvent()

    data class RegisterAttemptEvent(
        val email: String,
        val username: String,
        val password: String,
        val confirm_password: String
    ): AuthStateEvent()

    class CheckPreviousAuthEvent(): AuthStateEvent()
}