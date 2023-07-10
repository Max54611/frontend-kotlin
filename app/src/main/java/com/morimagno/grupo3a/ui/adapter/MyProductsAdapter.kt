package com.morimagno.grupo3a.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morimagno.grupo3a.R
import com.morimagno.grupo3a.data.adapter.inflate
import com.morimagno.grupo3a.data.database.entities.MyProductEntity
import com.morimagno.grupo3a.ui.holder.MyProductsHolder

class MyProductsAdapter (val list: List<MyProductEntity>):
    RecyclerView.Adapter<MyProductsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProductsHolder {
        val view = parent.inflate(R.layout.item_product_saved)
        return MyProductsHolder(view)
    }

    override fun onBindViewHolder(holder: MyProductsHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size
}