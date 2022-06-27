package com.tomuchcoffee.bookchasir.source.network

import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import org.koin.dsl.module

val repositoryModule = module {
    factory { BookChasirRepository(get()) }
}

class BookChasirRepository(
    private val api: ApiClient
) {
    suspend fun Sigin(
        email: String,
        password: String
    ): AuthResponse {
        return api.auth(
            email, password
        )
    }
}