package com.tomuchcoffee.bookchasir.source.network

import android.content.Context
import com.tomuchcoffee.bookchasir.util.PrefCanggi
import com.tomuchcoffee.bookchasir.util.SharePrefren
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        val prefren = SharePrefren(this)
        val token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7Im5hbWUiOiJhZG1pbiBjb2RlYXRob21lIiwidXNlcklkIjo1LCJyb2xlIjoiYWRtaW4ifSwiaWF0IjoxNjU2MzAyODkwfQ.BxsJbipy5TiBURfwcXfA1wswe-4Vxft0Egc55bx7lMU"
        val finalToken =  "Bearer "+token
        val request = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", finalToken)
            .build()

        return chain.proceed(request)
    }
}