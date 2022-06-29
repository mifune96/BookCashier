package com.tomuchcoffee.bookchasir.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tomuchcoffee.bookchasir.databinding.FragmentHomeBinding
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.ui.adapter.ProductAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import timber.log.Timber

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProducts()
        setView()
    }

    private fun setView() {
        binding.rvProduk.adapter = productAdapter
        viewModel.products.observe(viewLifecycleOwner, {
            Timber.e(it.data.toString())
            productAdapter.add(it.data!!)
        })
    }


    private val productAdapter by lazy {
        ProductAdapter(arrayListOf(), object: ProductAdapter.OnAdapterListener{
            override fun onClick(product: Products) {
                Toast.makeText(requireContext(),"Yey Berhasil clik", Toast.LENGTH_SHORT)
            }
        })
    }
}