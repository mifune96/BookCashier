package com.tomuchcoffee.bookchasir.source.model.auth

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("email")
    var username: String? = null,
    @SerializedName("password")
    var password: String? = null
)
