package com.tomuchcoffee.bookchasir.source.presistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tomuchcoffee.bookchasir.source.local.ProductDao
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.util.ProductConverter

@Database(
    entities = [Products::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ProductConverter::class)
abstract class DataBaseClient: RoomDatabase() {
    abstract val ProductDao:ProductDao
}