package com.morimagno.grupo3a.data.database.dao

import androidx.room.*
import com.morimagno.grupo3a.data.database.entities.MyProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: MyProductEntity)

    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts() : List<MyProductEntity>

    @Query("DELETE FROM product_table")
    suspend fun deleteTable();
}