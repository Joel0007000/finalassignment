package com.joel.finalassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joel.finalassignment.R
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.entity.Product
import java.lang.StringBuilder


class ProductAdapter(val lst:MutableList<Product>,val context: Context):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(view: View):RecyclerView.ViewHolder(view){
        var img: ImageView
        var name: TextView
        var desc:TextView
        var price:TextView
        var btn: TextView
        var rating: RatingBar


        init{

            img = view.findViewById(R.id.img)
            name = view.findViewById(R.id.name)
            desc = view.findViewById(R.id.description)
            price = view.findViewById(R.id.price)
            btn = view.findViewById(R.id.book)
            rating = view.findViewById(R.id.ratingbar)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.meroview,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val current = lst[position]
        holder.name.text=current.pname
        holder.desc.text=current.pdesc
        holder.price.text=current.pprice
Glide.with(context).load("${ServiceBuilder.BASE_URL}images/${current.pimage}").into(holder.img)
        holder.rating.rating=current.prating!!.toFloat()
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
    return lst.size
    }

}