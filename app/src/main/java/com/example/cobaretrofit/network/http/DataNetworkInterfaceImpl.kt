package com.example.cobaretrofit.network.http

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cobaretrofit.network.internal.NoConnectivityException
import com.example.cobaretrofit.network.response.Data

class DataNetworkInterfaceImpl(
    private val apiServices : ApiServices
) : DataNetworkInterface {
    private val _downloadData = MutableLiveData<Data>()
    override val downloadData: LiveData<Data>
        get() = _downloadData

    override suspend fun fetchData() {
        try {
            val fetchData = apiServices
                .getDataItem()
                .await()
            _downloadData.postValue(fetchData)
        } catch (e : NoConnectivityException){
            Log.e("Connectivity", "No Internet Connections", e)
        }
    }
}