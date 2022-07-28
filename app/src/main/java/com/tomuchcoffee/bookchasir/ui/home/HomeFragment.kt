package com.tomuchcoffee.bookchasir.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tomuchcoffee.bookchasir.databinding.FragmentHomeBinding
import com.tomuchcoffee.bookchasir.source.model.category.Data
import com.tomuchcoffee.bookchasir.source.model.checkout.CheckOutResponse
import com.tomuchcoffee.bookchasir.source.model.checkout.Payload
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.ui.adapter.CategoryAdapter
import com.tomuchcoffee.bookchasir.ui.adapter.ProductAdapterOld
import com.tomuchcoffee.bookchasir.ui.adapter.ProductCheckoutAdapter
import com.tomuchcoffee.bookchasir.util.ProductConverter
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

    val setupAdapter = ProductCheckoutAdapter(
        onClickPlusMinusListener = { asa ->
            viewModel.update(asa)
        },
        onClickDelListener = { asa ->
            viewModel.delet(asa)
        }
    )

    private fun setView() {
        viewModel.getProducts()
        binding.rvProduk.adapter = productAdapter
        viewModel.products.observe(viewLifecycleOwner, {
            productAdapter.clear()
            Timber.e(it.data.toString())
            it.data.let { it1 -> productAdapter.add(it1) }

        })

        binding.rvItemcheckout.layoutManager = LinearLayoutManager(activity)
        binding.rvItemcheckout.adapter = setupAdapter

        viewModel.showAllDao.observe(viewLifecycleOwner, {
            setupAdapter.submitList(it)
            Log.d(TAG, "isi dao: " + it)
            var countBuyPrice = 0.0
            var totalItem = 0
            for (d in it) {
                val hargaitem = d.price

                countBuyPrice += hargaitem * d.productbuyqty
                val g = d.productbuyqty
                totalItem += g
            }
            binding.tvTotalPayment.text = ProductConverter.decimalFormat(countBuyPrice)
            binding.tvCheckoutItem.text = totalItem.toString()
        })

        binding.tvClearAll.setOnClickListener {
            viewModel.deletAllProductDao()
        }


        binding.btnChekout.setOnClickListener {
            val asak = viewModel.showAllDao.value
            val payload = asak!!.map { produk ->
                Payload(
                    productId = produk.id, quantity = produk.productbuyqty
                )
            }

            val checkOutResponse = CheckOutResponse(
                payload = payload
            )

            viewModel.postCheckOut(
                checkOutResponse
            )

        }


        binding.edtSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.keyword = s.toString()
                viewModel.getProducts()
            }

        })

        viewModel.getCategory()
        binding.rvCategory.adapter = categoryAdapter
        viewModel.category.observe(viewLifecycleOwner,{
            categoryAdapter.clear()

            categoryAdapter.add(it.data!!)
        })


    }


    private val productAdapter by lazy {
        ProductAdapterOld(arrayListOf(), object : ProductAdapterOld.OnAdapterListener {
            override fun onClick(products: Products) {
                viewModel.incremenQty(products)
            }
        })

    }

    private val categoryAdapter by lazy {
        CategoryAdapter(arrayListOf(),object : CategoryAdapter.OnAdapterListener{
            override fun onClick(category: Data) {
                if (category.id == 0){
                    viewModel.getProducts()
                }
                viewModel.categoryid = category.id.toString()
                viewModel.getProductsByCategory()
                Log.d(TAG, "isi ID: "+ category.id)
            }

        })
    }

}

