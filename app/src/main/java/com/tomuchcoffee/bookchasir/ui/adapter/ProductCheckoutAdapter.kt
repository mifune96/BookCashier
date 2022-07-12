package com.tomuchcoffee.bookchasir.ui.adapter

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tomuchcoffee.bookchasir.BuildConfig
import com.tomuchcoffee.bookchasir.R
import com.tomuchcoffee.bookchasir.databinding.ProductCheckoutItemBinding
import com.tomuchcoffee.bookchasir.source.model.product.Products
import com.tomuchcoffee.bookchasir.util.ProductConverter

class ProductCheckoutAdapter(
    private val onClickPlusMinusListener: (Products) -> Unit,
    private val onClickDelListener: (Products) -> Unit
)   : ListAdapter<Products, ProductCheckoutAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductCheckoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: ProductCheckoutItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(product: Products) {
            with(itemBinding) {
                var harga = product.price
                var jumlah = product.productbuyqty

                Log.d(ContentValues.TAG, "isi qty: " + product.productbuyqty)
                tvHargaitemchekout.text =
                    ProductConverter.decimalFormat((harga * product.productbuyqty.toDouble()))
                tvJudulproduk.text = product.title
                tvStokchekoutitem.text = product.stock.toString()
                tvJumlahitemorder.text = jumlah.toString()

                btnTambah.setOnClickListener {
                    jumlah++
                    product.productbuyqty = jumlah
                    tvHargaitemchekout.text =
                        ProductConverter.decimalFormat((harga * product.productbuyqty.toDouble()))
                    tvJumlahitemorder.text = jumlah.toString()
                    onClickPlusMinusListener(product)

                }

                btnKurang.setOnClickListener {
                    if (jumlah <= 1) return@setOnClickListener
                    jumlah--
                    product.productbuyqty = jumlah
                    tvHargaitemchekout.text =
                        ProductConverter.decimalFormat((harga * product.productbuyqty.toDouble()))
                    tvJumlahitemorder.text = jumlah.toString()
                    onClickPlusMinusListener(product)


                }


                btnDeletitemchekout.setOnClickListener {
                    onClickDelListener(product)

                }


                Glide.with(root)
                    .load(BuildConfig.BASE_URL_IMG + product.cover)
                    .override(150, 200)
                    .centerCrop()
                    .error(R.drawable.sampelproduk)
                    .into(ivProduk)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Products>() {
            override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
                return oldItem.idProducts == newItem.idProducts
            }

            override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
                return oldItem == newItem
            }
        }
    }
}