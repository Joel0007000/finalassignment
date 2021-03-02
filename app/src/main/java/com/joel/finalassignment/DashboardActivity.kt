package com.joel.finalassignment

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    private val permissions = arrayOf( android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_dashboard)

        // Check for permission
        if (!hasPermission()) {
            requestPermission()
        }
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

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

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                permissions, 1
        )
    }
    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                            this,
                            permission
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
            }
        }
        return hasPermission
    }
}