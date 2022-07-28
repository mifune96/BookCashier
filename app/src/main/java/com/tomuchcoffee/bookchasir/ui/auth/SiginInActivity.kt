package com.tomuchcoffee.bookchasir.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tomuchcoffee.bookchasir.databinding.ActivitySiginInBinding
import com.tomuchcoffee.bookchasir.source.local.Datasharedpreferences
import com.tomuchcoffee.bookchasir.source.model.auth.AuthRequest
import com.tomuchcoffee.bookchasir.ui.home.MainActivity
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
        setContentView(ActivitySiginInBinding.inflate(layoutInflater).also { binding = it }.root)



        setView()


    }

    private fun setView() = with(binding) {
        btnSignIn.setOnClickListener {
            viewModel.login(
                AuthRequest(
                    tvEmail.text.toString(),
                    tvPassword.text.toString()
//                    "admin@gmail.com", "rahasia"

                )


            )
            dataOvserver()

        }
    }

    private fun dataOvserver() {
        viewModel._authMV.observe(this) {
            Datasharedpreferences.get.token = it.data?.token.orEmpty()
            val intent = Intent(this@SiginInActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
