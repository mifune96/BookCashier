package com.tomuchcoffee.bookchasir.source.network

import com.tomuchcoffee.bookchasir.source.model.Resource
import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import org.koin.dsl.module

val repositoryModule = module {
    factory { BookChasirRepository(get()) }
}

class BookChasirRepository(
    private val api: ApiClient,
//    private var prefren: SharePrefren

) {
    suspend fun Sigin(
        auth: AuthRequest
    ): Resource<AuthResponse> {
        val res = api.auth(auth)
//        prefren.setPref(Constant.TOKEN, res.data?.token.toString())
        return api.auth(auth)
    }


}