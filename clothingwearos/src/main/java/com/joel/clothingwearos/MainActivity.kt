package com.joel.clothingwearos

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.joel.clothingwearos.api.ServiceBuilder
import com.joel.clothingwearos.repository.WearRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : WearableActivity(),View.OnClickListener {
    private  lateinit var  etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin= findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener(this)
        // Enables Always-on
        setAmbientEnabled()
    }

    private fun authenticateUser()
    {
        if(TextUtils.isEmpty(etUsername.text))
        {
            etUsername.error = "Insert Username"
            etUsername.requestFocus()
        }
        else if(TextUtils.isEmpty(etPassword.text))
        {
            etPassword.error = "Insert Password"
            etPassword.requestFocus()
        }
        else
        {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repo = WearRepository()
                    val response = repo.loginUser(etUsername.text.toString(),etPassword.text.toString())
                    if(response.success == true)

                    }
                    else
                    {
                        withContext(Dispatchers.Main)
                        {
                            Snackbar.make(etUsername,"${response.message}", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
                catch (ex:Exception)
                {
                    println(ex.printStackTrace())
                    withContext(Dispatchers.Main)
                    {
                        Snackbar.make(etUsername,"${ex.toString()}", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.btnLogin ->{
                authenticateUser()
            }
        }
    }
}