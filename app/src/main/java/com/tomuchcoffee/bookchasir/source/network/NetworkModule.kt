package com.tomuchcoffee.bookchasir.source.network


import com.tomuchcoffee.bookchasir.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single { provideOkhtppClient() }
    single { provideRetrofit(get()) }
    single { provideBookChasirApi(get()) }
}

private val authInterceptor = AuthInterceptor()

fun provideOkhtppClient(): OkHttpClient{
    return OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .addInterceptor(authInterceptor)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideBookChasirApi(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)