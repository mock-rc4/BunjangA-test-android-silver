package com.example.risingtest.src.main.home.recommend

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.risingtest.databinding.RvMainRecoomendItemBinding
import com.example.risingtest.src.product.ProductActivity
import com.example.risingtest.src.product.ProductService

class RecommendRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<ProductData>) : RecyclerView.Adapter<RecommendRecyclerViewAdapter.ItemViewHolder>() {

    lateinit var binding: RvMainRecoomendItemBinding

    inner class ItemViewHolder(private var binding : RvMainRecoomendItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: ProductData) {
            binding.tvProductName.text = data.product_name
            binding.tvAddress.text = data.address
            binding.tvTime.text = data.time
            binding.tvZzimCount.text = data.productLike
            binding.tvProductPrice.text = data.product_price+"원"

            Glide
                .with(binding.ivProudctImg.context)
                .load(data.product_img)
                .into(binding.ivProudctImg)

            if(data.safety=="1"){
                binding.tvSafey.visibility= View.VISIBLE
                binding.ivProudctPay.visibility=View.VISIBLE
            }else {
                binding.tvSafey.visibility= View.GONE
                binding.ivProudctPay.visibility=View.GONE
            }

            itemView.setOnClickListener {
                Intent(context, ProductActivity::class.java).apply {
                    putExtra("idx",data.idx)
                    Log.d("idx",data.idx.toString())
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }


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