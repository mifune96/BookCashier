package com.tomuchcoffee.bookchasir.ui.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tomuchcoffee.bookchasir.databinding.ActivitySiginInBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val siginInModule = module {
    factory { SiginInActivity() }
}

class SiginInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiginInBinding
    private val viewModel: SigInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySiginInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setData()

    }

    private fun setData() {
        binding.apply {

            btnSignIn.setOnClickListener {
                   viewModel.login(tvEmail.text.toString(),tvPassword.text.toString())

            }
        }
    }


}