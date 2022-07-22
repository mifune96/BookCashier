package com.tomuchcoffee.bookchasir.ui.detail

import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.DialogDetailtransactionBinding
import com.tomuchcoffee.bookchasir.ui.adapter.DialogTransactionDetailAdapter
import com.tomuchcoffee.bookchasir.ui.transaction.TransactionViewModel
import com.tomuchcoffee.bookchasir.util.ProductConverter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogTransactionDetail : DialogFragment(){

    lateinit var binding:DialogDetailtransactionBinding
    private val viewModel: TransactionViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDetailtransactionBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setView()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private val dialogtransactiondetailadapter by lazy {
        DialogTransactionDetailAdapter(arrayListOf())
    }

    private fun setView() {

            binding.recyclerView2.layoutManager = LinearLayoutManager(activity)
            binding.recyclerView2.adapter = dialogtransactiondetailadapter
            viewModel.getTransaction()

            viewModel.transactions.observe(viewLifecycleOwner,{
                val data = it.data
                dialogtransactiondetailadapter.clear()
                it.data?.let { it1 -> dialogtransactiondetailadapter.addData(it1) }


                var date = ""
                var total_harga = 0.0
                for (d in data!!){
                    val detail = d.detailTransaction

                    date = d.date.toString()




                    for (x in detail!!){
                        val harga = x.priceProduct
                        total_harga += harga!! * x.quantity!!
                    }
                }


                binding.tvDatetimedetailtransaksi.text = date
                binding.tvTotalhargadetailtransaksi.text = ProductConverter.decimalFormat(total_harga)
                binding.tvTotalpaymentdetailtransaksi.text = ProductConverter.decimalFormat(total_harga)



            })


            binding.btnDone.setOnClickListener {
                Log.d(TAG, "Clik: ")
                dismiss()
            }


    }


}