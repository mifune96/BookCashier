package com.tomuchcoffee.bookchasir.source.local

import android.content.Context
import com.tomuchcoffee.bookchasir.ui.auth.SigInViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

val datasharedpreferencesModul = module {
    factory { Datasharedpreferences(androidContext()) }
}

class Datasharedpreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    var token: String
        get() = sharedPreferences.getString(Key.TOKEN, "").orEmpty()
        set(value) {
            sharedPreferences.edit()
                .putString(Key.TOKEN, value)
                .apply()
        }

    companion object : KoinComponent {
        const val NAME = "main_pref"

        val get: Datasharedpreferences = get()
    }

    object Key {
        const val TOKEN = "token"
    }
}