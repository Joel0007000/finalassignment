package com.joel.finalassignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.joel.finalassignment.Notification.NotificationSender
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.db.StudentDB
import com.joel.finalassignment.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var btn_newRegister: Button
    private lateinit var btn_loginGo: Button
    private lateinit var username: EditText
    private lateinit var password: EditText

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
        btn_loginGo.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val username12 = username.text.toString()
                    val password12 = password.text.toString()
                    val ur = UserRepository()
                    val response = ur.loginUser(username12, password12)
                    if (response.success == true) {
                        NotificationSender(this@LoginActivity,"Logged In Successfully","").createHighPriority()
                        var instance = StudentDB.getInstance(this@LoginActivity).getUserDAO()
                        instance.delete()
                        instance.userSignUp(response.data!!)
                        saveToSharedPref()
                        ServiceBuilder.token = "Bearer "+response.token
                        startActivity(Intent(this@LoginActivity, MainActivity2::class.java))
                    } else {
                        withContext(Main) {
                            Toast.makeText(this@LoginActivity, "Invalid Cerdentials", Toast.LENGTH_SHORT).show()
                        }
                    }


                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity, "Invalid ${ex}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }

    private fun saveToSharedPref()
    {
        var pref = getSharedPreferences("credentials",Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putString("username",username.text.toString())
        editor.putString("password",password.text.toString())
        editor.apply()
    }
}
