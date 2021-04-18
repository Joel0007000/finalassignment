package com.joel.finalassignment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.db.StudentDB
import com.joel.finalassignment.repository.UserRepository
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        var pref = getSharedPreferences("credentials",Context.MODE_PRIVATE)
        var username = pref.getString("username","")
        var password = pref.getString("password","")
        if(username != "" && password != "") {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val ur = UserRepository()
                    val response = ur.loginUser(username!!, password!!)
                    if (response.success == true) {
                        var instance = StudentDB.getInstance(this@MainActivity).getUserDAO()
                        instance.delete()
                        instance.userSignUp(response.data!!)
                        ServiceBuilder.token = "Bearer " + response.token
                        startActivity(Intent(this@MainActivity, MainActivity2::class.java))
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@MainActivity,
                                "Invalid Cerdentials",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }


                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Invalid ${ex}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }
       else
        {
            startActivity(
                Intent(
                    this@MainActivity,
                    LoginActivity::class.java
                )
            )
            finish()
        }



//        //declaring the animation
//        val top_animation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
//        val bottom_animation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
//
//        val imglogo = findViewById(R.id.imglogo) as ImageView
//        val tvheading = findViewById(R.id.tvheading) as TextView
//        val tvsentence = findViewById(R.id.tvsentence) as TextView
//
//        //Applying the animation
//        imglogo.startAnimation(top_animation)
//        tvheading.startAnimation(bottom_animation)
//        tvsentence.startAnimation(bottom_animation)


    }
}