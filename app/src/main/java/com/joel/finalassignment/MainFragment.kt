package com.joel.finalassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joel.finalassignment.adapter.ProductAdapter
import com.joel.finalassignment.db.StudentDB
import com.joel.finalassignment.entity.Product
import com.joel.finalassignment.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

var lst:MutableList<Product> = mutableListOf()
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        CoroutineScope(Dispatchers.IO).launch{
            var list:MutableList<Product> = mutableListOf()
            val ur = UserRepository()
            val response =ur.getProduct()
            if(response.success==true)
            {
                lst=response.data
               list= StudentDB.getInstance(container!!.context).getProductDAO().getProducts()
                if(lst.size>list.size)
                {
                    lst.clear()
                    for(data in lst)
                    {
                        StudentDB.getInstance(container.context).getProductDAO().insert(data)
                    }


                }

            }
withContext(Main){
    val rv:RecyclerView = view.findViewById(R.id.rv)
    rv.layoutManager=LinearLayoutManager(container!!.context)
    val adapter = ProductAdapter(list,container.context)//room database bata
    rv.adapter=adapter
}


        }

        return view
    }


}