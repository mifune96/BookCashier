package com.tomuchcoffee.bookchasir.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tomuchcoffee.bookchasir.BuildConfig
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ProductItemBinding
import com.tomuchcoffee.bookchasir.source.model.product.Products

class ProductAdapterOld(
    val products: ArrayList<Products>,
    val listener: OnAdapterListener
    ): RecyclerView.Adapter<ProductAdapterOld.ViewHolder>() {

    companion object{

    }

    class ViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Products){

            binding.tvAutor.setText(product.auhtor)
            binding.tvHargaprodukitem.setText(product.price.toString())
            binding.tvJudulproduk.setText(product.title)
            binding.tvStok.setText(product.stock.toString())
            binding.tvPublishdate.setText(product.published)
            binding.tvAutor.setText(product.auhtor)

            Glide.with(binding.root)
                .load(BuildConfig.BASE_URL+ product.cover)
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
        ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk  = products[position]
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