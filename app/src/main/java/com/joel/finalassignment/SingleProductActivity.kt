package com.joel.finalassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.entity.Booking
import com.joel.finalassignment.entity.Product
import com.joel.finalassignment.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SingleProductActivity : AppCompatActivity(),View.OnClickListener {
    private var furniture: Product? =null
    private lateinit var tvPrice: TextView

    private lateinit var tvProduct: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnAdd: Button
    private lateinit var ivFurniture: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_product)
        binding()
        initialize()
        btnAdd.setOnClickListener(this)
    }

    private fun binding()
    {
        tvPrice = findViewById(R.id.tvPrice)
        tvDescription = findViewById(R.id.tvDescription)
        tvProduct = findViewById(R.id.tvProduct)
        btnAdd = findViewById(R.id.btnAdd)
        ivFurniture = findViewById(R.id.ivFurniture)
        furniture = intent.getParcelableExtra("product")
    }

    private fun initialize()
    {

        tvPrice.text = "Rs "+furniture!!.pPrice
        tvDescription.text = furniture!!.pDesc
        tvProduct.text = furniture!!.pName

        var imgPath = ServiceBuilder.loadImgPath()+furniture!!.pImage!!.replace("\\","/")
        Glide.with(this@SingleProductActivity).load(imgPath).into(ivFurniture)

    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.btnAdd->{

                var booking = Booking(product_id = furniture,quantity = 1)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val bookingRepository = BookingRepository()
                        val response = bookingRepository.bookFurniture(booking)
                        if(response.success == true)
                        {
                           // NotificationSender(this@InstrumentsActivity,"Product Added To Cart!!","").createHighPriority()
                            withContext(Dispatchers.Main)
                            {
                                val snk = Snackbar.make(tvProduct,"Product Added to Cart", Snackbar.LENGTH_INDEFINITE)
                                snk.setAction("Go to Cart",View.OnClickListener {
                                    snk.dismiss()
                                })
                                snk.show()
                            }
                        }
                        else
                        {
                            withContext(Dispatchers.Main)
                            {
                                if(response.message == "Item already exists in cart.")
                                {
                                    val snk = Snackbar.make(tvProduct,"${response.message}", Snackbar.LENGTH_LONG)
                                    snk.setAction("Go to Cart",View.OnClickListener {
                                        snk.dismiss()
                                    })
                                    snk.show()
                                }
                                else
                                {
                                    val snk = Snackbar.make(tvProduct,"${response.message}", Snackbar.LENGTH_LONG)
                                    snk.setAction("OK",View.OnClickListener {
                                        snk.dismiss()
                                    })
                                    snk.show()
                                }

                            }
                        }

                    }
                    catch (ex: Exception)
                    {
                        withContext(Dispatchers.Main)
                        {
                            val snk = Snackbar.make(tvProduct,"${ex.toString()}", Snackbar.LENGTH_LONG)
                            snk.setAction("OK",View.OnClickListener {
                                snk.dismiss()
                            })
                            snk.show()
                            println(ex.printStackTrace())
                        }

                    }
                }

            }
        }
    }
}