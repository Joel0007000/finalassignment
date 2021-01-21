package com.joel.finalassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            startActivity(
                Intent(
                    this@MainActivity,
                    LoginActivity::class.java
                )
            )
            finish()
        }



        //declaring the animation
        val top_animation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        val bottom_animation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        val imglogo = findViewById(R.id.imglogo) as ImageView
        val tvheading = findViewById(R.id.tvheading) as TextView
        val tvsentence = findViewById(R.id.tvsentence) as TextView

        //Applying the animation
        imglogo.startAnimation(top_animation)
        tvheading.startAnimation(bottom_animation)
        tvsentence.startAnimation(bottom_animation)


    }
}