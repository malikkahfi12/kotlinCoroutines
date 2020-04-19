package com.example.cobaretrofit.network.http

import androidx.lifecycle.LiveData
import com.example.cobaretrofit.network.response.Data

interface DataNetworkInterface {
    val downloadData : LiveData<Data>

    suspend fun fetchData()
}