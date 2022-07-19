package com.tomuchcoffee.bookchasir.source.model.transaction


import com.google.gson.annotations.SerializedName

data class DetailTransactionResponse(
    @SerializedName("data")
    val `data`: List<Data2>? = listOf()
)

data class Data2(
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("detailTransaction")
    val detailTransaction: List<DetailTransaction2>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("invoice")
    val invoice: String? = ""
)

data class DetailTransaction2(
    @SerializedName("auhtorProduct")
    val auhtorProduct: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("priceProduct")
    val priceProduct: Int? = 0,
    @SerializedName("quantity")
    val quantity: Int? = 0,
    @SerializedName("titleProduct")
    val titleProduct: String? = ""
)