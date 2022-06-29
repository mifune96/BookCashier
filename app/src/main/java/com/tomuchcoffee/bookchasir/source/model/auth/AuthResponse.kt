package com.tomuchcoffee.bookchasir.source.model.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("data")
    val `data`: Data? = Data()
)

data class Data(
    @SerializedName("token")
    var token: String? = ""
)
