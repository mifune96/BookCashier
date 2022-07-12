package com.tomuchcoffee.bookchasir.source.network

import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.CheckOutRequest
import com.tomuchcoffee.bookchasir.source.model.checkout.Payload
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

    @POST("/transactions")
    suspend fun checkout(
        @Body respon: Payload
    ): CheckOutRequest
}