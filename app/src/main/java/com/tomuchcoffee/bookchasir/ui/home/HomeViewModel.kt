package com.tomuchcoffee.bookchasir.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomuchcoffee.bookchasir.source.model.product.ProductResponse
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.source.network.BookChasirRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
    val repository: BookChasirRepository
) : ViewModel() {

    val products by lazy { MutableLiveData<ProductResponse>() }
    val message by lazy { MutableLiveData<String>() }


    init {
        message.value = null
    }


    fun getProducts() {
        try {
            viewModelScope.launch {
                val data = repository.GetAllProduct()
                products.value = data
            }
        } catch (e: Exception) {
            message.value = "Terjadi Kesalahan"
        }

    }

    val showAllDao = repository.db.findAll()


    fun update(products: Products) {
        viewModelScope.launch {
            repository.update(products)
        }
    }

    fun delet(products: Products){
        viewModelScope.launch {
            repository.remove(products)
        }
    }

    fun incremenQty(products: Products){
        viewModelScope.launch {
            repository.incrementQty(products)
        }
    }

    fun deletAllProductDao(){
        viewModelScope.launch {
            repository.deleteAllDao()
        }
    }

}