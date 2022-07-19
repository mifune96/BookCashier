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
    private val onClickItemListener: (Data) -> Unit
): ListAdapter<Data, TransactionAdapter.ViewHolder>(DIF_CALLBACK){
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
        return holder.bind(getItem(position))
    }


    inner class ViewHolder(private val itemBinding: TransactionItemBinding):
            RecyclerView.ViewHolder(itemBinding.root){
                fun bind(data: Data){
                    with(itemBinding){

                        val nam = data.detailTransaction as ArrayList

                        val kol = nam.joinToString { detailTransaction: DetailTransaction ->
                            detailTransaction.titleProduct.toString()
                        }

                        val go = ArrayList<DetailTransaction>()
                        var nama =""
                        for (p in nam){
                            nama = p.titleProduct.toString()
                        }

                        tvIdProduktrankasi.text = data.invoice
                        tvTanggal.text = ConvertDateFormat().dateFormat2(data.date)
                        tvNamaProduktransaksi.text = kol
                        tvJumlahProduktrankasis.text = "+" +data.detailTransaction.size

                        Log.d(TAG, "isi nam: "+ kol)

                    }
                }


            }

    fun filterList(filterIdTransaction: ArrayList<Data>){

    }


    companion object{
        private val DIF_CALLBACK = object : DiffUtil.ItemCallback<Data>(){
            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }


}

