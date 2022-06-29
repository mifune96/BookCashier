package com.tomuchcoffee.bookchasir.util

import android.content.Context
import android.content.SharedPreferences
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse

class SharePrefren(context: Context) {
    private val PREF_NAME = "SHAREDPREFF"
    private val sharePref: SharedPreferences

    val editor: SharedPreferences.Editor

    init {
        sharePref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharePref.edit()
    }

    fun setPref(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String): String? {
        return sharePref.getString(key, null)
    }


}