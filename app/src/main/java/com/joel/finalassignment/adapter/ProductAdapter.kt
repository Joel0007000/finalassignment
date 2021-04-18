package com.joel.finalassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joel.finalassignment.R
import com.joel.finalassignment.SingleProductActivity
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.entity.Product

class ProductAdapter(val context: Context, val lstProduct:MutableList<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(val view: View):RecyclerView.ViewHolder(view)
    {
        var ivCart : ImageView
        var tvProductName : TextView
        var tvProductPrice : TextView
        var linear : LinearLayout
        init {
            ivCart = view.findViewById(R.id.ivImage)
            tvProductName = view.findViewById(R.id.tvProductName)
            tvProductPrice = view.findViewById(R.id.tvproductPrice)
            linear = view.findViewById(R.id.linear)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product,parent,false)
        return ProductViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var product = lstProduct[position]
        holder.tvProductName.text = product.pName
        holder.tvProductPrice.text = "$"+product.pPrice
        var imgPath = ServiceBuilder.loadImgPath()+product.pImage!!.replace("\\","/")

        Glide.with(context).load(imgPath).into(holder.ivCart)
        holder.linear.setOnClickListener {
            var intent = Intent(context, SingleProductActivity::class.java)
            intent.putExtra("product",product)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return lstProduct.size
    }
}