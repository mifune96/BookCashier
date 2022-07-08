package com.tomuchcoffee.bookchasir.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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


        binding.rvItemcheckout.layoutManager = LinearLayoutManager(activity)
        binding.rvItemcheckout.adapter = checkOutAdapter
        viewModel.showAllDao.observe(viewLifecycleOwner,{
            checkOutAdapter.clear()
            checkOutAdapter.add(it)

        })

    }

    private val checkOutAdapter by lazy {
        CheckOutAdapter(arrayListOf(), object : CheckOutAdapter.OnAdapterListener {
            override fun onClick(products: Products) {
                viewModel.update(products)

            }

            override fun onDelet(products: Products) {
                viewModel.delet(products)
            }

        })
    }


    private val productAdapter by lazy {
        ProductAdapterOld(arrayListOf(), object : ProductAdapterOld.OnAdapterListener {
            override fun onClick(products: Products) {

                if (products.productbuyqty==0){
                    products.productbuyqty=1
                    viewModel.insertData(products)
                } else{
                    products.productbuyqty+=1
                   viewModel.update(products)
                }


            }


        })

    }

}