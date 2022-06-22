package com.tomuchcoffee.bookchasir.source.model.auth

import java.io.Serializable

data class AuthResponse(
    val email: String,
    val password: String,
    val data: List<DataModel>,
)

data class DataModel(
    val token: String?
) : Serializable