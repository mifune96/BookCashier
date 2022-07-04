package com.tomuchcoffee.bookchasir.ui.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tomuchcoffee.bookchasir.databinding.FragmentHomeBinding
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.ui.adapter.CheckOutAdapter
import com.tomuchcoffee.bookchasir.ui.adapter.ProductAdapterOld
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

        setView()



    }


    private fun setView() {
        viewModel.getProducts()
        binding.rvProduk.adapter = productAdapter

        viewModel.products.observe(viewLifecycleOwner, {
            productAdapter.clear()
            Timber.e(it.data.toString())
            it.data.let { it1 -> productAdapter.add(it1) }
        })

        binding.rvItemcheckout.adapter = checkOutAdapter

        viewModel.produkdb.observe(viewLifecycleOwner,{
            checkOutAdapter.clear()
            checkOutAdapter.add(it)

        })



    }

    private val checkOutAdapter by lazy {
        CheckOutAdapter(arrayListOf(), object : CheckOutAdapter.OnAdapterListener{
            override fun onClick(product: Products) {
            }

        })
    }


    private val productAdapter by lazy {
        ProductAdapterOld(arrayListOf(), object : ProductAdapterOld.OnAdapterListener {
            override fun onClick(product: Products) {
                if(product == null){
                    viewModel.clickProduct(product)
                } else product.productbuyqty+=1

                Log.d(TAG, "produknya: "+ viewModel.clickProduct(product))
//                startActivity(Intent(requireActivity(), SiginInActivity::class.java))
                Toast.makeText(requireActivity(), "Yey Berhasil clik", Toast.LENGTH_SHORT)
            }
        })
    }

}