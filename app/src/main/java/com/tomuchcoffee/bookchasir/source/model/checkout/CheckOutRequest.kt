package com.tomuchcoffee.bookchasir.source.model.checkout


import com.google.gson.annotations.SerializedName

data class CheckOutRequest(
    @SerializedName("payload")
    val payload: List<Payload>? = listOf()
)