package com.morimagno.grupo3a.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.morimagno.grupo3a.data.database.dao.ProductDao
import com.morimagno.grupo3a.data.database.entities.MyProductEntity

@Database(entities = [MyProductEntity::class], version = 3)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    companion object {
        private const val DATABASE_NAME = "product_database"

        fun build(context: Context): ProductDatabase {
            return Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

