package com.tomuchcoffee.bookchasir.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tomuchcoffee.bookchasir.source.model.product.Products

@Dao
interface ProductDao {
    @Query("SELECT * FROM tableProduk")
    fun findAll() : LiveData<List<Products>>

    @Query("SELECT COUNT(*) FROM tableProduk WHERE published=:publish")
    suspend fun find(publish: String) : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(productModel: Products)

    @Update

    @Delete
    suspend fun remove(productModel: Products)
}