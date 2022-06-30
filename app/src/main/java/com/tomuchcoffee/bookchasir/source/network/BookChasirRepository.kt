package com.tomuchcoffee.bookchasir.source.network

import com.tomuchcoffee.bookchasir.source.model.Resource
import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.model.product.ProductModel
import com.tomuchcoffee.bookchasir.source.model.product.ProductResponse
import org.koin.dsl.module

val repositoryModule = module {
    factory { BookChasirRepository(get()) }
}

class BookChasirRepository(
    private val api: ApiClient

) {
    suspend fun Sigin(
        auth: AuthRequest
    ): AuthResponse {
        return api.auth(auth)
    }

    suspend fun GetAllProduct(
    ): ProductModel{
        return api.getProductAll()
    }


}