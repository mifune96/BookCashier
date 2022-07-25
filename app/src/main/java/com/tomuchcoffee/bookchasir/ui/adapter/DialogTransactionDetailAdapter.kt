package com.tomuchcoffee.bookchasir.ui.adapter


import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomuchcoffee.bookchasir.databinding.DetailTransactionItemBinding
import com.tomuchcoffee.bookchasir.source.model.transaction.Data2
import com.tomuchcoffee.bookchasir.source.model.transaction.DetailTransaction2

class DialogTransactionDetailAdapter(
    var dataList: ArrayList<DetailTransaction2>
) :
    RecyclerView.Adapter<DialogTransactionDetailAdapter.ViewHolder>() {

    class ViewHolder(val binding: DetailTransactionItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DetailTransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = dataList[position]
        with(holder) {


            with(binding) {
                val totalhargalist = transaction.priceProduct!! * transaction.quantity!!
                tvNameitemDetailproduk.text = transaction.titleProduct
                tvHargaItemDetailproduk.text = "$"+transaction.priceProduct
                tvJumlahhargaTotalitem.text = totalhargalist.toString()
                tvJumlahPeritemdetail.text = transaction.quantity.toString()
            }
        }
    }

    override fun getItemCount() = dataList.size

    fun addData(data: List<DetailTransaction2>?){
        dataList.addAll(data!!)
    }

    fun clear(){
        dataList.clear()
        notifyDataSetChanged()
    }


}

