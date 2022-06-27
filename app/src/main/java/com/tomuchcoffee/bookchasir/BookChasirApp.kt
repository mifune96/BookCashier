package com.tomuchcoffee.bookchasir

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.tomuchcoffee.bookchasir.source.network.networkModule
import com.tomuchcoffee.bookchasir.source.network.repositoryModule
import com.tomuchcoffee.bookchasir.ui.auth.siginInModule
import com.tomuchcoffee.bookchasir.ui.auth.siginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


class BookChasirApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.e("run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            androidLogger()
            androidContext(this@BookChasirApp)
            modules(
                networkModule,
                repositoryModule,
                siginViewModel,
                siginInModule
            )

        }
    }
}