package com.morimagno.grupo3a.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.morimagno.grupo3a.data.model.ProductResponse
import com.morimagno.grupo3a.data.network.ProductApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel:ViewModel() {

    private val _productList = MutableLiveData<List<ProductResponse>>()
    val productList: LiveData<List<ProductResponse>> get() = _productList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        _isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val productIds = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
            val productList = mutableListOf<ProductResponse>()

            for (productId in productIds) {
                val call = getRetroFit().create(ProductApi::class.java).getProduct(productId)
                if (call.isSuccessful) {
                    val product = call.body()
                    product?.let {
                        productList.add(product)
                    }
                }
            }

            _isLoading.postValue(false)
            _productList.postValue(productList)
        }
    }

    private fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ec2-3-145-131-89.us-east-2.compute.amazonaws.com:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}