package com.joel.finalassignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joel.finalassignment.R
import com.joel.finalassignment.adapter.ProductAdapter
import com.joel.finalassignment.db.StudentDB
import com.joel.finalassignment.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeFragment : Fragment() {
    private lateinit var recycler : RecyclerView
    private lateinit var adapter : ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        recycler = root.findViewById(R.id.recycler)
        initialize()
        return root
    }

    private fun initialize()
    {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repo = ProductRepository()
                val response = repo.getProducts()
                println(response)
                if (response.success == true) {
                    var products = response.data

                    val productDao = StudentDB.getInstance(requireContext()).getProductDAO()
                    productDao.deleteProducts()
                    for(product in products) {
                        productDao.insert(response.data)
                    }

                  withContext(Dispatchers.Main)
                  {
                      adapter = ProductAdapter(requireContext(),response.data)
                      recycler.adapter = adapter
                      recycler.layoutManager = GridLayoutManager(requireContext(),2)
                  }


                }

            }
            catch (ex:Exception)
            {
                withContext(Dispatchers.Main) {

                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}