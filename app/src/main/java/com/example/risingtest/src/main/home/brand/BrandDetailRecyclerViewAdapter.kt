package com.example.risingtest.src.main.home.brand

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R
import com.example.risingtest.databinding.RvMainBrandDetailItemBinding

class BrandDetailRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<BrandDetailData>) : RecyclerView.Adapter<BrandDetailRecyclerViewAdapter.Holder>() {

    lateinit var binding: RvMainBrandDetailItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = RvMainBrandDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class Holder(var binding: RvMainBrandDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val product_name : TextView = itemView.findViewById(R.id.tv_product_name)
        fun bind(item: BrandDetailData) {
//            binding.tvProductName = item.product_name
            product_name.text = item.product_name
        }
    }

}