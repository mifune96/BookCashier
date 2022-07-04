package com.tomuchcoffee.bookchasir.source.network

import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.model.product.ProductModel
import com.tomuchcoffee.bookchasir.source.model.product.ProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @POST("auth/signin")
    suspend fun auth(
        @Body respon: AuthRequest
    ): AuthResponse

    @GET("products?limit=10")
    suspend fun getProductAll(
    ): ProductResponse
}