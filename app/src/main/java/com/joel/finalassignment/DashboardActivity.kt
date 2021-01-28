package com.joel.finalassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_dashboard)
    }
}