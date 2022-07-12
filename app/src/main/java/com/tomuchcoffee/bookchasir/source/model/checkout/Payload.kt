package com.tomuchcoffee.bookchasir.source.model.checkout


import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("productId")
    val productId: Int? = 0,
    @SerializedName("quantity")
    val quantity: Int? = 0
)