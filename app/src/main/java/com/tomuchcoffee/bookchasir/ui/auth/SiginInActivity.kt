package com.tomuchcoffee.bookchasir.ui.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tomuchcoffee.bookchasir.databinding.ActivitySiginInBinding
import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.ui.home.MainActivity
import com.tomuchcoffee.bookchasir.util.Constant
import com.tomuchcoffee.bookchasir.util.SharePrefren
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val siginInModule = module {
    factory { SiginInActivity() }
}

class SiginInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiginInBinding
    private val viewModel: SigInViewModel by viewModel()

    private val prefren by lazy { SharePrefren(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivitySiginInBinding.inflate(layoutInflater).also { binding = it }.root)


        dataOvserver()
        setView()

    }

    private fun setView() = with(binding) {
        btnSignIn.setOnClickListener {
            viewModel.login(
                AuthRequest(
                    tvEmail.text.toString(),
                    tvPassword.text.toString()
                )
            )

            val intent = Intent(this@SiginInActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun dataOvserver() {
        viewModel._authMV.observe(this){
            prefren.setPref(Constant.TOKEN, it.data?.token.toString())
            Log.d(TAG, "dataOvserver: " +it.data?.token.toString())
        }
    }

//    private fun dataOvserver() {
//        viewModel.authMV.observe(this) {
//            when (it.status) {
//                Resource.Status.SUCCESS -> {
//                    Log.d(TAG, "dataOvserver: " + it.data?.data)
//                    prefren.setPref(Constant.TOKEN, it.data?.data?.token.orEmpty())
//                    binding.progressBar.hide()
//
//                    val intent = Intent(this,MainActivity::class.java)
//                    startActivity(intent)
//
//
//                }
//
//                Resource.Status.LOADING -> {
//                    binding.progressBar.show()
//                }
//                Resource.Status.ERROR -> {
//                    binding.progressBar.hide()
//                    Toast.makeText(applicationContext, "Ups Try Again Or check your Connection", 3)
//                        .show()
//                }
//
//            }
//        }
//    }
}
