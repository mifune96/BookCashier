package com.tomuchcoffee.bookchasir.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tomuchcoffee.bookchasir.source.model.product.Products

object ProductConverter {

    @TypeConverter
    @JvmStatic
    fun toSource(value: String?) : Products{
        val modelType = object : TypeToken<Products>(){}.type
        return Gson().fromJson(value, modelType)
    }

    @TypeConverter
    @JvmStatic
    fun FromSource(source: Products) : String{
        val gson = Gson()
        return gson.toJson(source)
    }
}