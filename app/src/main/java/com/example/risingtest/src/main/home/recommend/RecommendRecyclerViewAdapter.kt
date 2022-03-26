package com.example.risingtest.src.main.home.recommend

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.databinding.RvMainRecoomendItemBinding
import com.example.risingtest.src.product.ProductActivity

class RecommendRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<ProductData>) : RecyclerView.Adapter<RecommendRecyclerViewAdapter.ItemViewHolder>() {

    lateinit var binding: RvMainRecoomendItemBinding

    inner class ItemViewHolder(private var binding : RvMainRecoomendItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: ProductData) {
            binding.tvProductName.text = data.product_name
            binding.tvAddress.text = data.address
            binding.tvTime.text = data.time
            binding.ivProudctImg.setImageResource(data.product_img)

            itemView.setOnClickListener {
                Intent(context, ProductActivity::class.java).apply {
                    putExtra("idx",data.idx)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

//            Glide
//                .with(binding.ivBook.context)
//                .load(data.img)
//                .into(binding.ivBook)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // viewholder를 생성하는 부분
        binding = RvMainRecoomendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // 데이터를 그려주게되는 함수
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("ddddd","dddd")
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}