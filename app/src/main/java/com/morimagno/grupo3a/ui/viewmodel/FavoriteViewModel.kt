package com.morimagno.grupo3a.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.morimagno.grupo3a.data.database.ProductDatabase
import com.morimagno.grupo3a.data.database.entities.MyProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(): ViewModel() {

    private val PRODUCT_DATABASE_NAME = "product_database"

    val myProductList = MutableLiveData<List<MyProductEntity>>()

    fun getMyProducts(context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            val myProducts = getRoomDataBase(context).getProductDao().getAllProducts()
            myProductList.postValue(myProducts)
        }
    }

    fun deleteAllProducts(context: Context){
        viewModelScope.launch {
            getRoomDataBase(context).getProductDao().deleteTable()
            myProductList.postValue(emptyList())
        }
    }

    private fun getRoomDataBase(context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            PRODUCT_DATABASE_NAME).build()
    }
}