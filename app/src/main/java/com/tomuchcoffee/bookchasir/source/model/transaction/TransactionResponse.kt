package com.tomuchcoffee.bookchasir.source.model.transaction


import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("data")
    val `data`: List<Data>? = listOf()
) data class Data(
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("detailTransaction")
    val detailTransaction: List<DetailTransaction>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("invoice")
    val invoice: String? = "",
    @SerializedName("user")
    val user: Int? = 0
) data class DetailTransaction(
    @SerializedName("auhtorProduct")
    val auhtorProduct: String? = "",
    @SerializedName("coverImage")
    val coverImage: String? = "",
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("priceProduct")
    val priceProduct: Int? = 0,
    @SerializedName("productHistoryId")
    val productHistoryId: Int? = 0,
    @SerializedName("quantity")
    val quantity: Int? = 0,
    @SerializedName("titleProduct")
    val titleProduct: String? = "",
    @SerializedName("transaction")
    val transaction: Int? = 0,
    @SerializedName("updatedAt")
    val updatedAt: String? = ""
)