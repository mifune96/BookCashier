package com.tomuchcoffee.bookchasir.ui.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tomuchcoffee.bookchasir.BuildConfig
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ProductItemBinding
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.util.ConvertDateFormat

class ProductAdapterOld(
    val products: ArrayList<Products>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<ProductAdapterOld.ViewHolder>() {


    class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    interface OnAdapterListener {
        fun onClick(product: Products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = products[position]
        with(holder) {
            binding.tvAutor.text = produk.auhtor
            binding.tvHargaprodukitem.text = produk.price.toString()
            binding.tvJudulproduk.text = produk.title
            binding.tvStok.text = produk.stock.toString()
            binding.tvPublishdate.text = ConvertDateFormat().dateFormat(produk.createdAt)

            Glide.with(binding.root)
                .load(BuildConfig.BASE_URL_IMG+ produk.cover)
                .override(150, 200)
                .centerCrop()
                .error(R.drawable.sampelproduk)
                .into(binding.ivProduk)
            binding.itemProduct.setOnClickListener {

                listener.onClick(produk)

            }
        }

    }

    fun add(data: List<Products>) {
        products.addAll(data)
        notifyItemRangeInserted((products.size - data.size), data.size)
    }

    fun clear() {
        products.clear()
        notifyDataSetChanged()
    }
}