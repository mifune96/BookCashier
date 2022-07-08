package com.tomuchcoffee.bookchasir.source.model.product


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class ProductResponse(
    val data: List<Products>,
)

@Entity(tableName = "tableProduk")
data class Products(
    val auhtor: String?,
    val category: Int?,
    val cover: String?,
    val createdAt: String?,
    val id: Int?,
    val price: Double,
    @PrimaryKey(autoGenerate = false)
    val published: String,
    val sold: Int?,
    val stock: Int,
    val title: String?,
    val updatedAt: String?,
    val user: Int?,

    val totalpayment : Int?,
    val totalproductbuy: Int?,
    var productbuyqty: Int=0,

    ): Serializable