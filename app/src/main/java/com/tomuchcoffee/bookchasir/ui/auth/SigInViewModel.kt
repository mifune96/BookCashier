package com.tomuchcoffee.bookchasir.ui.auth

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomuchcoffee.bookchasir.source.local.Datasharedpreferences
import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.network.BookChasirRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val siginViewModel = module {
    factory { SigInViewModel(get()) }
}

class SigInViewModel(
    val repository: BookChasirRepository
) : ViewModel() {
    val _authMV by lazy { MutableLiveData<AuthResponse>() }
    val message by lazy { MutableLiveData<String>() }

    init {
        message.value = null
    }

    fun login(authReq: AuthRequest) {
        viewModelScope.launch {
            try {
                val response = repository.Sigin(authReq)
                _authMV.value = response

                Datasharedpreferences.get.token = response.data?.token.toString()
                Log.d(TAG, "Resource isinya: " + response.data?.token.toString())

            } catch (e: Exception) {
                message.value = "Terjadi Kesalahan"
            }
        }
    }


}