package com.tomuchcoffee.bookchasir.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tomuchcoffee.bookchasir.BuildConfig
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ProductCheckoutItemBinding
import com.tomuchcoffee.bookchasir.databinding.ProductItemBinding
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.util.ConvertDateFormat

class CheckOutAdapter(
    val products: ArrayList<Products>,
    val listener: OnAdapterListener
): RecyclerView.Adapter<CheckOutAdapter.ViewHolder>() {

    companion object{

    }

    class ViewHolder(val binding: ProductCheckoutItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Products){

            binding.tvHargaitemchekout.setText(product.price.toString())
            binding.tvJudulproduk.setText(product.title)
            binding.tvStokchekoutitem.setText(product.stock.toString())
            binding.tvJumlahitemorder.setText(product.productbuyqty.toString())

            Glide.with(binding.root)
                .load(BuildConfig.BASE_URL_IMG+ product.cover)
                .override(150, 200)
                .centerCrop()
                .error(R.drawable.sampelproduk)
                .into(binding.ivProduk)
        }
    }

    interface OnAdapterListener{
        fun onClick(product: Products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ProductCheckoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk  = products[position]
        holder.bind(produk)
        holder.itemView.setOnClickListener {
            listener.onClick(produk)
        }
    }

    fun add(data: List<Products>){
        products.addAll(data)
        notifyItemRangeInserted((products.size - data.size), data.size)
    }

    fun clear(){
        products.clear()
        notifyDataSetChanged()
    }
}