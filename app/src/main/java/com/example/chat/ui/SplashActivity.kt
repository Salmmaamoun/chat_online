package com.example.chat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.chat.R
import com.example.chat.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable{
            override fun run() {
                val intent= Intent(this@SplashActivity,LoginActivity::class.java)
                startActivity(intent)
            }

        },3000)
    }
}