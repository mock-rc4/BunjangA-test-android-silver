package com.example.risingtest.src.main.home.recommend

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.databinding.RvMainBrandDetailItemBinding
import com.example.risingtest.databinding.RvMainBrandItemBinding
import com.example.risingtest.src.main.home.brand.BrandData
import com.example.risingtest.src.main.home.brand.BrandDetailData

class BrandDetailRecyclerViewAdapter(private val dataList: ArrayList<BrandDetailData>) : RecyclerView.Adapter<BrandDetailRecyclerViewAdapter.ItemViewHolder>() {

    lateinit var binding: RvMainBrandDetailItemBinding

    inner class ItemViewHolder(private val binding : RvMainBrandDetailItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: BrandDetailData) {
//            binding.tvProductName.text = data.product_name
//            binding.tvAddress.text = data.address
//            binding.tvTime.text = data.time
//            binding.ivProudctImg.setImageResource(data.product_img)
//            Glide
//                .with(binding.ivBook.context)
//                .load(data.img)
//                .into(binding.ivBook)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // viewholder를 생성하는 부분
        binding = RvMainBrandDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // 데이터를 그려주게되는 함수
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}