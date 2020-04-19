package com.example.cobaretrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cobaretrofit.R
import com.example.cobaretrofit.network.response.Data
import com.example.cobaretrofit.network.response.DataItem
import kotlinx.android.synthetic.main.item_data_view.view.*

class DataAdapter(private val dataList:List<DataItem>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_data_view, parent, false)
        return DataViewHolder(itemView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.textTitle.text = dataList.get(position).title
        holder.textDescription.text = dataList.get(position).body
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textTitle  : TextView = itemView.text_title
        val textDescription : TextView = itemView.text_description
    }
}