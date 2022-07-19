package com.tomuchcoffee.bookchasir.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ActivityMainBinding
import com.tomuchcoffee.bookchasir.source.local.Datasharedpreferences
import com.tomuchcoffee.bookchasir.ui.auth.SigInViewModel
import com.tomuchcoffee.bookchasir.ui.detail.TransactionFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val mainInModule = module {
    factory { MainActivity() }
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SigInViewModel by viewModel()


    private val fragmentHome = HomeFragment()
    private val fragmentTraction = TransactionFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        replaceFragment(fragmentHome)
        binding.apply {
            navRail.setOnItemSelectedListener { menu->
                when(menu.itemId){
                    R.id.ic_card->{
                        replaceFragment(fragmentHome)
                        true
                    }
                    R.id.ic_transaksi->{
                        replaceFragment(fragmentTraction)
                        true
                    }

                    else-> false
                }
            }
        }



    }

    private fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.nav_host_fragment_containercoy, fragment)
        fm.commit()

    }
}