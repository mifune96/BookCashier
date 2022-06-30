package com.tomuchcoffee.bookchasir.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tomuchcoffee.bookchasir.databinding.FragmentHomeBinding
import com.tomuchcoffee.bookchasir.ui.adapter.ProductAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment(), ProductAdapter.ProducItemListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ProductAdapter
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

        setupObservers()
        setView()

    }

    private fun setView() {
        adapter = ProductAdapter(this)
        binding.rvProduk.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProduk.adapter = adapter

//        binding.rvProduk.adapter = productAdapter
//        viewModel.products.observe(viewLifecycleOwner, {
//            Timber.e(it.data.toString())
//            productAdapter.add(it.data!!)
//        })
    }


//    private val productAdapter by lazy {
//        ProductAdapterOld(arrayListOf(), object: ProductAdapterOld.OnAdapterListener{
//            override fun onClick(product: Products) {
//                Toast.makeText(requireContext(),"Yey Berhasil clik", Toast.LENGTH_SHORT)
//            }
//        })
//    }

    private fun setupObservers() {
        viewModel.getProducts()
        viewModel.products.observe(viewLifecycleOwner,{
            adapter.setItem(ArrayList(it.data))
        })
    }

    override fun OnClickItem(id: Int) {
    }
}