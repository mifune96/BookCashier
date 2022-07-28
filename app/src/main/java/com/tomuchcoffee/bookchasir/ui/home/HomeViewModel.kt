package com.tomuchcoffee.bookchasir.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomuchcoffee.bookchasir.source.model.category.CategoryResponse
import com.tomuchcoffee.bookchasir.source.model.category.Data
import com.tomuchcoffee.bookchasir.source.model.checkout.CheckOutResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.Payload
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
    val category by lazy { MutableLiveData<CategoryResponse>() }
    val checkout by lazy {MutableLiveData<CheckOutResponse>()}
    val message by lazy { MutableLiveData<String>() }


    init {
        message.value = null
    }

    val showAllDao = repository.db.findAll()
    var keyword = ""
    var categoryid = ""


    fun getProducts() {
        try {
            viewModelScope.launch {
                val data = repository.GetAllProduct(keyword)
                products.value = data
            }
        } catch (e: Exception) {
            message.value = "Terjadi Kesalahan"
        }

    }

    fun getProductsByCategory() {
        try {
            viewModelScope.launch {
                val data = repository.GetProductByCategory(keyword,categoryid)
                products.value = data
            }
        } catch (e: Exception) {
            message.value = "Terjadi Kesalahan"
        }

    }

    fun getCategory(){
        viewModelScope.launch {
            val datas = repository.GetAllCategoryt()
            val mergedData = listOf<Data>(Data(0, "All")).plus(datas.data)
            val dataa = datas.copy(data = mergedData)


            category.value = dataa
        }
    }


    fun postCheckOut(checkOutResponse: CheckOutResponse){
        viewModelScope.launch {
            val response = repository.postCheckout(checkOutResponse)
            checkout.value = response

            deletAllProductDao()
            getProducts()
        }
    }




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