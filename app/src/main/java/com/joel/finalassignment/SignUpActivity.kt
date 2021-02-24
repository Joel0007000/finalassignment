package com.joel.finalassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.joel.finalassignment.api_entity.UserTable
import com.joel.finalassignment.db.StudentDB
import com.joel.finalassignment.entity.User
import com.joel.finalassignment.repository.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.lang.Exception

@Suppress("DEPRECATION")
class SignUpActivity : AppCompatActivity() {

    private lateinit var btn_Have_an_account: Button
    private lateinit var btn_SignUpGO: Button

    private lateinit var fullname: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var password: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_sign_up)

        btn_Have_an_account = findViewById(R.id.btn_Have_an_account)
        btn_SignUpGO = findViewById(R.id.btn_SignUpGO)

        fullname = findViewById(R.id.fullname)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        password = findViewById(R.id.password)



        btn_SignUpGO.setOnClickListener {


            val fullname = fullname.text.toString()
            val username = username.text.toString()
            val email = email.text.toString()
            val phone = phone.text.toString()
            val password = password.text.toString()

            //code to insert in db
            val user = UserTable(fullname = fullname, email = email, username = username,
                    phone = phone, password = password)
            CoroutineScope(Dispatchers.IO).launch {
                 catch (err: Exception) {
                    withContext(Main)
                    {
                        Toast.makeText(this@SignUpActivity, "${err}", Toast.LENGTH_SHORT).show()

                    }
                }


            }
        }
    }
}