package com.tomuchcoffee.bookchasir.source.model.product


import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("data")
    val `data`: List<Data>? = listOf()
)