package com.morimagno.grupo3a.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.morimagno.grupo3a.data.model.ProductResponse
import com.morimagno.grupo3a.R
import com.morimagno.grupo3a.databinding.ItemProductBinding

class ProductAdapter(
    var list: List<ProductResponse>,
    var callback: Callback
): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_product)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = list[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemProductBinding.bind(view)

        fun bind(producto: ProductResponse){
            with(binding){
                root.setOnClickListener{
                    callback.onClickProductInformation(producto)
                }
                tvName.text = producto.productName
                Glide
                    .with(itemView)
                    .load(producto.getProductImage())
                    .into(binding.ivProducto)
            }
        }
    }

    interface Callback {
        fun onClickProductInformation(producto: ProductResponse)
    }
}

fun ViewGroup.inflate(layoutRes: Int) : View =
    LayoutInflater.from(context).inflate(layoutRes,this,false)