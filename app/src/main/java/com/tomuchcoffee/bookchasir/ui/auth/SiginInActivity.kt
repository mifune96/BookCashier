package com.tomuchcoffee.bookchasir.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tomuchcoffee.bookchasir.databinding.ActivitySiginInBinding

class SiginInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiginInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySiginInBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}