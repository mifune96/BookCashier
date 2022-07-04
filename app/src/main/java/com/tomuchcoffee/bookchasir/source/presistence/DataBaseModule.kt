package com.tomuchcoffee.bookchasir.source.presistence

import android.app.Application
import androidx.room.Room
import com.tomuchcoffee.bookchasir.source.local.ProductDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideProduct(get()) }
}

fun provideDatabase(application: Application): DataBaseClient {
    return Room.databaseBuilder(
        application,
        DataBaseClient::class.java,
        "ProductDB1169.db"
    ).fallbackToDestructiveMigration()
        .build()
}

fun provideProduct(databaseClient: DataBaseClient): ProductDao {
    return databaseClient.ProductDao
}