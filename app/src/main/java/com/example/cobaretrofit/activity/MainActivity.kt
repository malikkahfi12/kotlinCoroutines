package com.example.cobaretrofit.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cobaretrofit.R
import com.example.cobaretrofit.adapter.DataAdapter
import com.example.cobaretrofit.network.http.ApiServices
import com.example.cobaretrofit.network.http.ConnectivityInterceptorImpl
import com.example.cobaretrofit.network.http.DataNetworkInterfaceImpl
import com.example.cobaretrofit.network.response.DataItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stateLoading(false)
        getDataItem()
    }

    fun getDataItem(){
        // Call ApiService
        val apiServices = ApiServices(ConnectivityInterceptorImpl(this))
        val dataNetworkInterfaceImpl = DataNetworkInterfaceImpl(apiServices)
        dataNetworkInterfaceImpl.downloadData.observe(this, Observer { data ->
            // State Loading
            stateLoading(true)
            // Loop
            val dataItemList = ArrayList<DataItem>()

            // Setup RecyclerView
            recycler_view.adapter = DataAdapter(dataItemList)
            recycler_view.layoutManager = LinearLayoutManager(this)
            recycler_view.setHasFixedSize(true)

            for (position in 0 until data.size){
                val dataTitle : String = data.get(position).title
                val dataDescription : String = data.get(position).body
                val dataId : Int = data.get(position).id
                val dataUserId : Int = data.get(position).userId

                val dataItem = DataItem(body = dataDescription, id = dataId, title = dataTitle, userId = dataUserId)
                dataItemList += dataItem
            }
        })

        GlobalScope.launch(Dispatchers.Main){
            dataNetworkInterfaceImpl.fetchData()
        }
    }

    fun stateLoading(state: Boolean) : Boolean{
        if (state) loading_bar.visibility = View.GONE else loading_bar.visibility = View.VISIBLE
        return state
    }
}
