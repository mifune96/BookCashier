package com.tomuchcoffee.bookchasir.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tomuchcoffee.bookchasir.BuildConfig
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ProductCheckoutItemBinding
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.ui.home.HomeViewModel
import com.tomuchcoffee.bookchasir.util.ProductConverter

class AdapterCheckout(private val viewModel: HomeViewModel) :
    RecyclerView.Adapter<AdapterCheckout.ViewHolder>() {

    private var products = emptyList<Products>()

    class ViewHolder(val binding: ProductCheckoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ProductCheckoutItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = products[position]
        with(holder) {
            var harga = produk.price
            var jumlah = products[position].productbuyqty
            binding.tvHargaitemchekout.text =
                ProductConverter.decimalFormat((harga * produk.productbuyqty.toDouble()))
            binding.tvJudulproduk.text = produk.title
            binding.tvStokchekoutitem.text = produk.stock.toString()
            binding.tvJumlahitemorder.text = jumlah.toString()


            binding.btnTambah.setOnClickListener {
                jumlah++
                produk.productbuyqty = jumlah
                binding.tvHargaitemchekout.text =
                    ProductConverter.decimalFormat((harga * produk.productbuyqty.toDouble()))
                viewModel.update(produk)
                binding.tvJumlahitemorder.text = jumlah.toString()

            }

            Glide.with(binding.root)
                .load(BuildConfig.BASE_URL_IMG + produk.cover)
                .override(150, 200)
                .centerCrop()
                .error(R.drawable.sampelproduk)
                .into(binding.ivProduk)
        }
    }

    override fun getItemCount() = products.size

    fun setData(product: List<Products>) {
        this.products = product
        notifyDataSetChanged()
    }

}