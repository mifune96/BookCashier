package com.tomuchcoffee.bookchasir.util

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ConvertDateFormat{
    fun dateFormat(date: String?) : String{
        return if (date.isNullOrEmpty()) ""
        else{
            val currentFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val dateParse = currentFormat.parse(date)
            val toFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            toFormat.format(dateParse)
        }
    }

    fun dateFormat2(date: String?) : String{
        return if (date.isNullOrEmpty()) ""
        else{
            val currentFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val dateParse = currentFormat.parse(date)
            val toFormat = SimpleDateFormat("d MMM yyyy hh:mm aaa", Locale.getDefault())
            toFormat.format(dateParse)
        }
    }

}