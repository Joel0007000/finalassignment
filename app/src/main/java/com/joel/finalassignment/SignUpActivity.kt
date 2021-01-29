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
import com.joel.finalassignment.db.StudentDB
import com.joel.finalassignment.entity.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class SignUpActivity : AppCompatActivity() {

    private lateinit var btn_Have_an_account: Button
    private lateinit var btn_SignUpGO: Button

    private lateinit var fname: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var phoneNo: TextInputEditText
    private lateinit var password: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_sign_up)

        btn_Have_an_account = findViewById(R.id.btn_Have_an_account)
        btn_SignUpGO = findViewById(R.id.btn_SignUpGO)

        fname = findViewById(R.id.fname)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        phoneNo = findViewById(R.id.phoneNo)
        password = findViewById(R.id.password)



        btn_SignUpGO.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)

            val fname = fname.text.toString()
            val username = username.text.toString()
            val email = email.text.toString()
            val phoneNo = phoneNo.text.toString()
            val password = password.text.toString()

            //code to insert in db
            val user = User(fname,username,email, phoneNo,password)
            CoroutineScope(Dispatchers.IO).launch {
                StudentDB.getInstance(this@SignUpActivity)!!.getUserDAO()
                        .userSignUp(user)



        }
        }
    }
}