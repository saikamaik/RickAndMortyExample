package com.example.rickandmortyexample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyexample.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val binding: ActivitySplashScreenBinding =
            ActivitySplashScreenBinding.inflate(layoutInflater)
        binding.rickAndMortyLogo.alpha = 0f
        val secondsDelayed = 1
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, (secondsDelayed * 1000).toLong())
        }
    }
