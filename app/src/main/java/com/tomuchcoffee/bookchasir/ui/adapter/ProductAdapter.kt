package com.tomuchcoffee.bookchasir.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tomuchcoffee.bookchasir.BuildConfig
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ProductItemBinding
import com.tomuchcoffee.bookchasir.source.model.product.Data
import com.tomuchcoffee.bookchasir.util.ConvertDateFormat

class ProductAdapter(private val listener: ProducItemListener): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val produc = ArrayList<Data>()
    interface ProducItemListener{
        fun OnClickItem(id: Int)
    }

    fun setItem(datas: ArrayList<Data>){
        this.produc.clear()
        this.produc.addAll(datas)
        notifyDataSetChanged()
    }

    class ProductViewHolder(
        private val binding : ProductItemBinding,
        private val listener: ProductAdapter.ProducItemListener
    ):RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        private lateinit var products: Data

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(p: Data){
            this.products = p
            binding.tvJudulproduk.text = p.title
            binding.tvHargaprodukitem.text = p.price.toString()
            binding.tvAutor.text = p.auhtor
            binding.tvStok.text = p.stock.toString()
            binding.tvPublishdate.text = ConvertDateFormat().dateFormat(p.published.toString())
            Glide.with(binding.root)
                .load( BuildConfig.BASE_URL_IMG+ p.cover)
                .override(150, 200)
                .centerCrop()
                .error(R.drawable.sampelproduk)
                .into(binding.ivProduk)

        }

        override fun onClick(v: View?) {
            listener.OnClickItem(products.id!!)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding : ProductItemBinding  = ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding, listener)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(produc[position])

    override fun getItemCount() = produc.size
}