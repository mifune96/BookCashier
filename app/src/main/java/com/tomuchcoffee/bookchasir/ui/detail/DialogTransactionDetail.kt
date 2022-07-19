package com.tomuchcoffee.bookchasir.ui.detail

import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        binding = DialogDetailtransactionBinding.inflate(LayoutInflater.from(context))
//        return AlertDialog.Builder(requireActivity())
//            .setView(binding.root)
//            .create()
//    }

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

    private val dialogtransactiondetailadapter by lazy {
        DialogTransactionDetailAdapter(arrayListOf())
    }

    private fun setView() {
        with(binding){
            viewModel.transactions.observe(viewLifecycleOwner,{
                val data = it.data

                var date = ""
                var total_harga = 0.0
                for (d in data!!){
                    val detail = d.detailTransaction

                    date = d.date.toString()

                    binding.recyclerView2.layoutManager = LinearLayoutManager(activity)
                    binding.recyclerView2.adapter = dialogtransactiondetailadapter
                    viewModel.getTransaction()

                    dialogtransactiondetailadapter.clear()
                    dialogtransactiondetailadapter.addData(detail!!)

                    Log.d(TAG, "isiDetail: " +detail)

                    for (x in detail!!){
                        val harga = x.priceProduct
                        total_harga += harga!! * x.quantity!!
                    }
                }


                tvDatetimedetailtransaksi.text = date
                tvTotalhargadetailtransaksi.text = ProductConverter.decimalFormat(total_harga)
                tvTotalpaymentdetailtransaksi.text = ProductConverter.decimalFormat(total_harga)
            })


            binding.btnDone.setOnClickListener {
                Log.d(TAG, "Clik: ")
                dismiss()
            }

        }
    }


}