package com.tomuchcoffee.bookchasir.ui.home

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ActivityMainBinding
import com.tomuchcoffee.bookchasir.util.Constant
import com.tomuchcoffee.bookchasir.util.SharePrefren

class MainActivity : AppCompatActivity() {

    private lateinit var prefren: SharePrefren
    private lateinit var binding: ActivityMainBinding

    private val fragmentHome = HomeFragment()
//    private val fragmentTransaction = Transaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)



        prefren = SharePrefren(this)

        replaceFragment(fragmentHome)
        binding.apply {
            navRail.setOnItemReselectedListener {
                when(it.itemId){
                    R.id.ic_card -> replaceFragment(fragmentHome)

                }
            }
        }



        Log.d(TAG, "isi pref: "+prefren.getString(Constant.TOKEN))


    }

    private fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.nav_host_fragment_containercoy, fragment)
        fm.commit()

    }
}