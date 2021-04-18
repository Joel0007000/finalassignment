package com.joel.clothingwearos

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.joel.clothingwearos.repository.WearRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DashboardActivity : WearableActivity() {
    private lateinit var tvCart: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        tvCart = findViewById(R.id.tvCart)
        // Enables Always-on

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val bookingRepository = WearRepository()
                val response = bookingRepository.retrieveBooking()
                if(response.success == true)
                {
                    withContext(Dispatchers.Main)
                    {

                        tvCart.text = "${response.data!!.size} items on cart."
                    }
                }
                else
                {
                    withContext(Dispatchers.Main)
                    {

                        tvCart.text = "Cart is Empty"
                    }
                }
            }
