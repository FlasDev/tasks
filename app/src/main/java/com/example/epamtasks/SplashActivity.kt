package com.example.epamtasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)
        Thread.sleep(Random.nextLong(3000, 5000))
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}
