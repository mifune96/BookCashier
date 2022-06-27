package com.tomuchcoffee.bookchasir.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.network.BookChasirRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val siginViewModel = module {
    factory { SigInViewModel(get()) }
}

class SigInViewModel(
    val repository: BookChasirRepository
): ViewModel(){
    val authMV by lazy { MutableLiveData<AuthResponse>() }
    val message by lazy { MutableLiveData<String>() }

    init {
        message.value = null
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            try {
                val response = repository.Sigin(email, password)
                authMV.value = response
            } catch (e : Exception){
                message.value = "Terjadi Kesalahan"
            }
        }
    }
}