package com.tomuchcoffee.bookchasir.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomuchcoffee.bookchasir.source.model.Resource
import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.source.model.auth.AuthResponse
import com.tomuchcoffee.bookchasir.source.network.BookChasirRepository
import com.tomuchcoffee.bookchasir.util.SharePrefren
import kotlinx.coroutines.launch
import org.koin.dsl.module

val siginViewModel = module {
    factory { SigInViewModel(get()) }
}

class SigInViewModel(
    val repository: BookChasirRepository
): ViewModel(){
    private val _authMV by lazy { MutableLiveData<Resource<AuthResponse>>() }
    val authMV  : LiveData<Resource<AuthResponse>> = _authMV
    val message by lazy { MutableLiveData<String>() }

    init {
        message.value = null
    }

    fun login(authReq: AuthRequest){
        viewModelScope.launch {
            try {
                val response = repository.Sigin(authReq)
                if(response.status == Resource.Status.SUCCESS){
                    _authMV.postValue(Resource.success(response.data))
                }
//                when(response.status){
//                    Resource.Status.SUCCESS -> _authMV.postValue(Resource.success(response.data))
//                    Resource.Status.LOADING -> pro
//                        Resource.Status.ERROR ->
//                }

            } catch (e : Exception){
                message.value = "Terjadi Kesalahan"
            }
        }
    }


}