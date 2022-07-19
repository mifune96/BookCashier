package com.tomuchcoffee.bookchasir.ui.transaction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomuchcoffee.bookchasir.source.model.transaction.TransactionResponse
import com.tomuchcoffee.bookchasir.source.network.BookChasirRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val transactionViewModel = module {
    factory { TransactionViewModel(get()) }
}

class TransactionViewModel(
    val repository: BookChasirRepository
) : ViewModel() {

    val transactions by lazy { MutableLiveData<TransactionResponse>() }
    val message by lazy { MutableLiveData<String>() }

    init {
        message.value = null
    }

    var keyword = ""

    fun getTransaction(){
        viewModelScope.launch {
            try {
                val response = repository.getAllTransaction(keyword)
                transactions.value = response

            } catch (e: Exception){
                message.value = "Terjadi Kesalahan"
            }
        }
    }


}