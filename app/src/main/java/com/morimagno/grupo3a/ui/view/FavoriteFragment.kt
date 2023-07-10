package com.morimagno.grupo3a.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.morimagno.grupo3a.R
import com.morimagno.grupo3a.data.database.entities.MyProductEntity
import com.morimagno.grupo3a.databinding.FragmentFavoriteBinding
import com.morimagno.grupo3a.ui.adapter.MyProductsAdapter
import com.morimagno.grupo3a.ui.viewmodel.FavoriteViewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate){

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private val listMyProduct = mutableListOf<MyProductEntity>()

    private val adapter by lazy { MyProductsAdapter(listMyProduct) }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        favoriteViewModel.getMyProducts(requireContext())

        binding.rvProducts.adapter = adapter

        favoriteViewModel.myProductList.observe(this){
            listMyProduct.clear()
            listMyProduct.addAll(it)
            adapter.notifyDataSetChanged()
        }

        binding.floatingActionDelete.setOnClickListener{
            favoriteViewModel.deleteAllProducts(requireContext())
        }
    }
}