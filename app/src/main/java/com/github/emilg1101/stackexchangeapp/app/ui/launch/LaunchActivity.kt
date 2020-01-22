package com.github.emilg1101.stackexchangeapp.app.ui.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.emilg1101.stackexchangeapp.app.ui.main.MainActivity
import com.github.emilg1101.stackexchangeapp.core.extensions.openActivity

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openActivity(MainActivity::class.java)
        finish()
    }
}
