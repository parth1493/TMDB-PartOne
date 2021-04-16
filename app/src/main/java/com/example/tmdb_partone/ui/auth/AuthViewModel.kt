package com.example.tmdb_partone.ui.auth

import androidx.lifecycle.ViewModel
import com.example.tmdb_partone.repository.auth.AuthRepository

class AuthViewModel constructor(
    val authRepository: AuthRepository,
): ViewModel() {
}