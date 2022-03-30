package com.example.risingtest.src.address

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.databinding.RvAddressItemBinding

class AddressRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<AddressData>) : RecyclerView.Adapter<AddressRecyclerViewAdapter.ItemViewHolder>() {

    lateinit var binding: RvAddressItemBinding

    inner class ItemViewHolder(private var binding : RvAddressItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: AddressData) {
            binding.tvName.text = data.name
            binding.tvAddress.text = data.address
            binding.tvPhoneNumber.text = data.phoneNumber

//            itemView.setOnClickListener {
//                Intent(context, ProductActivity::class.java).apply {
//                    putExtra("idx",data.idx)
//                    Log.d("idx",data.idx.toString())
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                }.run { context.startActivity(this) }
//            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // viewholder를 생성하는 부분
        binding = RvAddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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