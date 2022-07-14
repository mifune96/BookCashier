package com.tomuchcoffee.bookchasir.source.model.checkout


import com.google.gson.annotations.SerializedName

data class CheckOutResponse(
    @SerializedName("payload")
    val payload: List<Payload>? = listOf()
)

