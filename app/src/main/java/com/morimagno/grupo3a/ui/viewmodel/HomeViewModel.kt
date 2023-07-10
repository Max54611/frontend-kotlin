package com.morimagno.grupo3a.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.morimagno.grupo3a.data.database.ProductDatabase
import com.morimagno.grupo3a.data.database.entities.MyProductEntity
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

    private val PRODUCT_DATABASE_NAME = "product_database"

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

    fun saveProduct(productResponse: ProductResponse,context: Context){
        val myProduct = MyProductEntity(
            product_name = productResponse.productName,
            image = productResponse.getProductImage(),
            description = productResponse.description,
            product_id = productResponse.productId,
            marked_price = "S/${productResponse.markedPrice.toString()}0"
        )

        viewModelScope.launch {
            getRoomDatabase(context).getProductDao().insert(myProduct)
        }
    }

    private fun getRoomDatabase(context: Context): ProductDatabase{
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            PRODUCT_DATABASE_NAME
        ).build()
    }

    private fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ec2-3-145-131-89.us-east-2.compute.amazonaws.com:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}