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
    val produk by lazy { MutableLiveData<Products>() }
    val message by lazy { MutableLiveData<String>() }
    val isClick by lazy { MutableLiveData<Int>(0) }

    val produkdb = repository.db.findAll()

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

    fun find(products: Products) {
        viewModelScope.launch {
          repository.find(products)
        }
    }

    fun clickProduct(products: Products) {
        viewModelScope.launch {

            repository.save(products)

        }
    }

}