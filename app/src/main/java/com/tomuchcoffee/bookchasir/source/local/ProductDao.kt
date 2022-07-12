package com.tomuchcoffee.bookchasir.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.tomuchcoffee.bookchasir.source.model.product.Products

@Dao
interface ProductDao {
    @Query("SELECT * FROM tableProduk ORDER BY idProducts ASC")
    fun findAll() : LiveData<List<Products>>

    @Query("SELECT * FROM tableProduk WHERE id=:id")
    suspend fun getOneProduct(id: Int): Products

    @Query("SELECT COUNT(*) FROM tableProduk WHERE published=:publish")
    suspend fun find(publish: String) : Int

    @Query("DELETE FROM tableProduk")
    suspend fun deletAllProduck()

    @Insert(onConflict = REPLACE)
    suspend fun save(productModel: Products)

    @Update
    suspend fun update(productModel: Products)

    @Delete
    suspend fun remove(productModel: Products)
}