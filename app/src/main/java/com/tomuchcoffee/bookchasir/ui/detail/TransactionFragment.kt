package com.tomuchcoffee.bookchasir.ui.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.FragmentTransactionBinding
import com.tomuchcoffee.bookchasir.source.model.transaction.Data
import com.tomuchcoffee.bookchasir.ui.adapter.TransactionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import java.util.Locale.filter

val transactionModule = module {
    factory { TransactionFragment() }
}
class TransactionFragment : Fragment(){

    private lateinit var binding: FragmentTransactionBinding
    private val viewModel: TransactionViewModel by viewModel()
    private var data = ArrayList<Data>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()

//        binding.edtSearch.setOnQ


        binding.edtSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
               viewModel.keyword = s.toString()
                viewModel.getTransaction()
            }

        })

    }

    private val transactionAdapter by lazy {
        TransactionAdapter(arrayListOf(),object : TransactionAdapter.onAdapterListener{
            override fun onClick(data: Data) {

            }

        })
    }

    private fun setView() {

        binding.rvProduk.layoutManager = LinearLayoutManager(activity)
        binding.rvProduk.adapter = transactionAdapter
        viewModel.getTransaction()
        viewModel.transactions.observe(viewLifecycleOwner,{
            transactionAdapter.clear()
            it.data?.let { it1 -> transactionAdapter.addData(it1) }
        })



    }
}

