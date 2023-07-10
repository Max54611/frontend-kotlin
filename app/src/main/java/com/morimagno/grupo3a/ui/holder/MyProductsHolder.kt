package com.morimagno.grupo3a.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.morimagno.grupo3a.data.database.entities.MyProductEntity
import com.morimagno.grupo3a.databinding.ItemProductSavedBinding

class MyProductsHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductSavedBinding.bind(view)

    fun bind(product: MyProductEntity){
        with(binding) {
            tvIndex.text = formatNumberTo3Digits(product.product_id.toInt())
            tvName.text=product.product_name
            tvPrice.text=product.marked_price
            Glide
                .with(itemView)
                .load(product.image)
                .into(ivProductimg)
        }
    }

    fun formatNumberTo3Digits(number: Int): String = "#${"%03d".format(number)}"
}