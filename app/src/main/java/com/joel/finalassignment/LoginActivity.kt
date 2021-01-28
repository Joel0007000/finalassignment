package com.joel.finalassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    private lateinit var btn_newRegister: Button
    private lateinit var btn_loginGo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        btn_newRegister = findViewById(R.id.btn_newRegister)
        btn_loginGo = findViewById(R.id.btn_loginGo)

        btn_newRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            }
        btn_loginGo.setOnClickListener{
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)

        }
    }
}