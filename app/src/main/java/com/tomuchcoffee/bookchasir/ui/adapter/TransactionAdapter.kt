package com.tomuchcoffee.bookchasir.ui.adapter


import android.content.ContentValues.TAG
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tomuchcoffee.bookchasir.databinding.TransactionItemBinding
import com.tomuchcoffee.bookchasir.source.model.transaction.Data
import com.tomuchcoffee.bookchasir.source.model.transaction.DetailTransaction
import com.tomuchcoffee.bookchasir.util.ConvertDateFormat

class TransactionAdapter(
    var dataList: ArrayList<Data>,
    val listener: onAdapterListener
) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(val binding: TransactionItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    interface onAdapterListener{
        fun onClick(data: Data)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = dataList[position]
        with(holder) {
            val name = transaction.detailTransaction as ArrayList

            val nameProduct = name.joinToString { detailTransaction: DetailTransaction ->
                detailTransaction.titleProduct.toString()
            }

            with(binding) {
                tvIdProduktrankasi.text = transaction.invoice
                tvNamaProduktransaksi.text = nameProduct
                tvJumlahProduktrankasis.text = "+" + transaction.detailTransaction.size
                tvTanggal.text = ConvertDateFormat().dateFormat2(transaction.date)

                btnTransaksidetail.setOnClickListener {
                    listener.onClick(transaction)
                }
            }
        }
    }

    override fun getItemCount() = dataList.size

    fun addData(data: List<Data>){
        dataList.addAll(data)
    }

    fun clear(){
        dataList.clear()
        notifyDataSetChanged()
    }


}

