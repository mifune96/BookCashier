package com.tomuchcoffee.bookchasir.source.network

import android.content.ContentValues.TAG
import android.util.Log
import com.tomuchcoffee.bookchasir.source.local.Datasharedpreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val token = Datasharedpreferences.get
        val finalToken =  "Bearer "+token.token
        val request = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", finalToken)
            .build()

        return chain.proceed(request)
    }
}