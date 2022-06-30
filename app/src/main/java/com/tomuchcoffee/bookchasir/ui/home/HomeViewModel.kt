package com.tomuchcoffee.bookchasir.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomuchcoffee.bookchasir.source.model.product.ProductModel
import com.tomuchcoffee.bookchasir.source.model.product.ProductResponse
import com.tomuchcoffee.bookchasir.source.network.BookChasirRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
    val repository: BookChasirRepository
) : ViewModel() {

    val products by lazy { MutableLiveData<ProductModel>() }
    val message by lazy { MutableLiveData<String>() }

    init {
        message.value = null
    }


    fun getProducts(){
        try {
            viewModelScope.launch {
                val data  = repository.GetAllProduct()
                products.value = data
            }
        }catch (e: Exception){
            message.value = "Terjadi Kesalahan"
        }

    }

}