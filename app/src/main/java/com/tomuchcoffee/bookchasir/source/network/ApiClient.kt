package com.tomuchcoffee.bookchasir.source.network

import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.model.category.CategoryResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.CheckOutResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.Payload
import com.tomuchcoffee.bookchasir.source.model.product.ProductResponse
import com.tomuchcoffee.bookchasir.source.model.transaction.DetailTransactionResponse
import com.tomuchcoffee.bookchasir.source.model.transaction.TransactionResponse
import retrofit2.http.*

interface ApiClient {

    @POST("auth/signin")
    suspend fun auth(
        @Body respon: AuthRequest
    ): AuthResponse

    @GET("products?limit=10")
    suspend fun getProductAll(
        @Query("keyword") keyword: String
    ): ProductResponse

    @GET("products?limit=10")
    suspend fun getProductByCategory(
        @Query("keyword") keyword: String,
        @Query("category") category: String,
    ): ProductResponse

    @POST("transactions")
    suspend fun checkout(
        @Body checkOutResponse: CheckOutResponse
    ): CheckOutResponse

    @GET("transactions")
    suspend fun getAllTransaction(
        @Query("keyword") keyword: String
    ): TransactionResponse

    @GET("transactions/{transactionId}")
    suspend fun getDetailTransaction(
      @Path("transactionId") id: Int
    ): DetailTransactionResponse

    @GET("categories?limit=20")
    suspend fun getAllCategory(
    ): CategoryResponse




}