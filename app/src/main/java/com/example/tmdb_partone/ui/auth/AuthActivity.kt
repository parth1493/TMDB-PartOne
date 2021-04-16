package com.example.tmdb_partone.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tmdb_partone.R
import com.example.tmdb_partone.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

   private lateinit var authBinding:ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

    }
}
