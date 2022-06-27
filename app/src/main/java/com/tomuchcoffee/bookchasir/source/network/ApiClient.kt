package com.tomuchcoffee.bookchasir.source.network

import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {

    @FormUrlEncoded
    @POST("auth/signin")
    suspend fun auth(
       @Field("email") email: String,
       @Field("password") password: String
    ): AuthResponse
}