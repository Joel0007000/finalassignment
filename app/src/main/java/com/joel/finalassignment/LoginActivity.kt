package com.joel.finalassignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.joel.finalassignment.db.StudentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var btn_newRegister: Button
    private lateinit var btn_loginGo: Button
    private lateinit var username : TextInputEditText
    private lateinit var password : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        btn_newRegister = findViewById(R.id.btn_newRegister)
        btn_loginGo = findViewById(R.id.btn_loginGo)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        btn_newRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            }
        btn_loginGo.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {
                var user = StudentDB.getInstance(this@LoginActivity).getUserDAO().authentication(username.text.toString(),password.text.toString())
                if(user != null)
                {
                    var getPref = getSharedPreferences("pref", Context.MODE_PRIVATE)
                    var editor = getPref.edit()
                    editor.putString("username",username.text.toString())
                    editor.putString("password",password.text.toString())
                    editor.apply()
                    CoroutineScope(Dispatchers.IO).launch {
                        val intent = Intent(this@LoginActivity,DashboardActivity::class.java)
                        startActivity(intent)
                    }
                }
                else
                {
                    withContext(Main)
                    {
                        Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}