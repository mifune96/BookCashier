package com.tomuchcoffee.bookchasir.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tomuchcoffee.bookchasir.databinding.CategoryItemBinding
import com.tomuchcoffee.bookchasir.source.model.category.Data

class CategoryAdapter(
    val category: ArrayList<Data>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val items = arrayListOf<TextView>()
    private val items2 = arrayListOf<View>()

    class ViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)



    interface OnAdapterListener {
        fun onClick(category: Data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = category.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categorys = category[position]
        with(holder) {
            binding.tvCategory.text = categorys.name
            items.add(binding.tvCategory)
            items2.add(binding.vwLine)

            itemView.setOnClickListener {
                listener.onClick(categorys)
                setColor(binding.tvCategory)
                setView(binding.vwLine)
            }
            setColor(items[0])//setcolor default
            setView(items2[0])

        }

    }


    private fun setColor(textView: TextView){
        items.forEach{it.setTextColor(Color.GRAY)}
        textView.setTextColor(Color.BLACK)
    }

    private fun setView(view: View){
        items2.forEach{it.visibility = View.GONE}
        view.visibility = View.VISIBLE
    }



    fun add(data: List<Data>) {
        category.addAll(data)
    }

    fun clear() {
        category.clear()
        notifyDataSetChanged()
    }
}

