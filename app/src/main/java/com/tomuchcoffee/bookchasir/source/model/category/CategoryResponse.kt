package com.tomuchcoffee.bookchasir.source.model.category


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    val `data`: List<Data> = listOf()
) data class Data(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = ""
)