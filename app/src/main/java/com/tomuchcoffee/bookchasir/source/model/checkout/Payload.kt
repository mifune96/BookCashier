package com.tomuchcoffee.bookchasir.source.model.checkout

import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("productId")
    var productId: Int? = 0,
    @SerializedName("quantity")
    var quantity: Int? = 0
)