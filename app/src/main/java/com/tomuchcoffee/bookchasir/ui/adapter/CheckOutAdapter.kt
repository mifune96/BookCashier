package com.tomuchcoffee.bookchasir.ui.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tomuchcoffee.bookchasir.BuildConfig
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ProductCheckoutItemBinding
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.util.ProductConverter

class CheckOutAdapter(
    val products: ArrayList<Products>  ,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<CheckOutAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProductCheckoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    interface OnAdapterListener {
        fun onClick(product: Products)
        fun onDelet(product: Products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ProductCheckoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = products[position]
        with(holder) {
            var harga = produk.price
            var jumlah = products[position].productbuyqty

            Log.d(TAG, "isi qty: " + produk.productbuyqty)
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
                binding.tvJumlahitemorder.text = jumlah.toString()
                listener.onClick(produk)

            }

            binding.btnKurang.setOnClickListener {
                if (jumlah <= 1) return@setOnClickListener
                jumlah--
                produk.productbuyqty = jumlah
                binding.tvHargaitemchekout.text =
                    ProductConverter.decimalFormat((harga * produk.productbuyqty.toDouble()))
                binding.tvJumlahitemorder.text = jumlah.toString()
                listener.onClick(produk)

            }


            binding.btnDeletitemchekout.setOnClickListener {
                listener.onDelet(produk)
            }



            Glide.with(binding.root)
                .load(BuildConfig.BASE_URL_IMG + produk.cover)
                .override(150, 200)
                .centerCrop()
                .error(R.drawable.sampelproduk)
                .into(binding.ivProduk)
        }

    }

    fun add(data: List<Products>) {
        val diffUtil = MyDiffUtil(products, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        products.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

//    fun clear() {
//        products.clear()
//        notifyDataSetChanged()
//    }
}