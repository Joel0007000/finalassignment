package com.joel.finalassignment

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.joel.finalassignment.Notification.NotificationSender
import com.joel.finalassignment.ui.dashboard.DashboardFragment
import com.joel.finalassignment.ui.home.HomeFragment
import com.joel.finalassignment.ui.notifications.NotificationsFragment

class MainActivity2 : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor?=null
    private var sensor2: Sensor?=null
    private var sensor3: Sensor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val frame:FrameLayout= findViewById(R.id.frame)
currentfragment(HomeFragment())
navView.setOnNavigationItemSelectedListener {
    when(it.itemId)
    {
        R.id.navigation_dashboard->
        {
            currentfragment(DashboardFragment())
        }

        R.id.navigation_notifications->{
            currentfragment(NotificationsFragment())
        }

        R.id.navigation_home->{
            currentfragment(HomeFragment())
        }
    }

    true
}

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        if(!checkSensor())
        {
            return
        }
        else
        {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensor3 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
            sensorManager.registerListener(this,sensor2,SensorManager.SENSOR_DELAY_NORMAL)
            sensorManager.registerListener(this,sensor3,SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    private fun checkSensor():Boolean
    {
        var flag = true
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null)
        {
            flag = false
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null)
        {
            flag = false
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null)
        {
            flag = false
        }
        return flag
    }


    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.sensor.type == Sensor.TYPE_PROXIMITY)
        {
            val values = event!!.values[0]
            if(values <= 4)
            {
                val logOut = Logout(this,this)
                logOut.logout()
            }
        }
        if(event!!.sensor.type == Sensor.TYPE_LIGHT)
        {
            val values = event!!.values[0]
            if(values > 40)
            {
                NotificationSender(this,"High Light can damage your eyes","").createHighPriority()
            }
        }
        if(event!!.sensor.type == Sensor.TYPE_ACCELEROMETER)
        {
            val values = event!!.values
            val x_axis = values[0]
            if(x_axis >= 7 && x_axis < 10)
            {
                var intent = Intent(this,SignUpActivity::class.java)
                startActivity(intent)
            }
            else if (x_axis <= -7 && x_axis > -10)
            {
                var intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

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