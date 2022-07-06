package com.tomuchcoffee.bookchasir.ui.home

import androidx.lifecycle.LiveData
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

    val readAllCheckout: LiveData<List<Products>>

    val produkdb = repository.db.findAll()

    init {
        message.value = null

        readAllCheckout = repository.readAllDataProductDao
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

    fun find(products: Products) {
        viewModelScope.launch {
            repository.find(products)
        }
    }

    fun insertData(products: Products) {
        viewModelScope.launch {
            repository.save(products)
        }
    }

    fun update(products: Products) {
        viewModelScope.launch {
            repository.update(products)
        }
    }

}