package com.tomuchcoffee.bookchasir.source.network

import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.CheckOutResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.Payload
import com.tomuchcoffee.bookchasir.source.model.product.ProductResponse
import com.tomuchcoffee.bookchasir.source.model.transaction.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {

    @POST("auth/signin")
    suspend fun auth(
        @Body respon: AuthRequest
    ): AuthResponse

    @GET("products?limit=10")
    suspend fun getProductAll(
    ): ProductResponse

    @POST("transactions")
    suspend fun checkout(
        @Body checkOutResponse: CheckOutResponse
    ): CheckOutResponse

    @GET("transactions")
    suspend fun getAllTransaction(
        @Query("keyword") keyword: String
    ): TransactionResponse


}