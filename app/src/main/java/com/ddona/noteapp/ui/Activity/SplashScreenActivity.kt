package com.ddona.noteapp.ui.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ddona.noteapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                                       startActivity(Intent(this, MainActivity::class.java))
        },2000)
    }
}