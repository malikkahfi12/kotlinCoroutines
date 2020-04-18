package com.example.cobaretrofit.network.response


import com.google.gson.annotations.SerializedName

data class DataItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)