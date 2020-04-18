package com.example.cobaretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.cobaretrofit.network.http.ApiServices
import com.example.cobaretrofit.network.http.ConnectivityInterceptorImpl
import com.example.cobaretrofit.network.http.DataNetworkInterface
import com.example.cobaretrofit.network.http.DataNetworkInterfaceImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call ApiService
        val apiServices = ApiServices(ConnectivityInterceptorImpl(this))
        val dataNetworkInterfaceImpl = DataNetworkInterfaceImpl(apiServices)

        dataNetworkInterfaceImpl.downloadData.observe(this, Observer {
            text.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main){
            dataNetworkInterfaceImpl.fetchData()
        }
    }
}
