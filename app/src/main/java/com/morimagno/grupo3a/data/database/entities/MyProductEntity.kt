package com.morimagno.grupo3a.data.database.entities

import androidx.room.*

@Entity(tableName = "product_table")
data class MyProductEntity(
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id") val id: Int = 0,
    @ColumnInfo(name="product_id") val product_id: Int,
    @ColumnInfo(name="description") val description: String,
    @ColumnInfo(name="image_url") val image: String,
    @ColumnInfo(name="product_name") val product_name: String,
    @ColumnInfo(name="marked_price") val marked_price: String,
    @ColumnInfo(name="position") val position: Int = -1
)