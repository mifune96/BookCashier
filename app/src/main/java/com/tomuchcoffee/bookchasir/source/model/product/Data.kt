package com.tomuchcoffee.bookchasir.source.model.product


import com.google.gson.annotations.SerializedName



data class Data(
    @SerializedName("auhtor")
    val auhtor: String? = "",
    @SerializedName("category")
    val category: Int? = 0,
    @SerializedName("cover")
    val cover: String? = "",
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("price")
    val price: Double? = 0.0,
    @SerializedName("published")
    val published: String? = "",
    @SerializedName("sold")
    val sold: Int? = 0,
    @SerializedName("stock")
    val stock: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("updatedAt")
    val updatedAt: String? = "",
    @SerializedName("user")
    val user: Int? = 0
)