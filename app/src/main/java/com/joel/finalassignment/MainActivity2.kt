package com.joel.finalassignment

import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val frame:FrameLayout= findViewById(R.id.frame)
currentfragment(MainFragment())
navView.setOnNavigationItemSelectedListener {
    when(it.itemId)
    {R.id.navigation_dashboard->
        {
            currentfragment(MainFragment())
        }
    }

    true
}

    }
    fun currentfragment( fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame,fragment)
            addToBackStack(null)
            commit()
        }
    }
}