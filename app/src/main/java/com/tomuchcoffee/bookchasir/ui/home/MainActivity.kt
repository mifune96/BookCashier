package com.tomuchcoffee.bookchasir.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ActivityMainBinding
import com.tomuchcoffee.bookchasir.ui.auth.SigInViewModel
import com.tomuchcoffee.bookchasir.ui.transaction.TransactionFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val mainInModule = module {
    factory { MainActivity() }
}

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: SigInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.navRail.setupWithNavController(navController)

    }


}