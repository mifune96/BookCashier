package com.tomuchcoffee.bookchasir.ui.home

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.util.Constant
import com.tomuchcoffee.bookchasir.util.SharePrefren

class MainActivity : AppCompatActivity() {

    private lateinit var prefren: SharePrefren

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefren = SharePrefren(this)



        Log.d(TAG, "isi pref: "+prefren.getString(Constant.TOKEN))


    }
}