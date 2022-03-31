package com.example.risingtest.src.address

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.databinding.RvAddressItemBinding

class AddressRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<AddressData>) : RecyclerView.Adapter<AddressRecyclerViewAdapter.ItemViewHolder>() {

    lateinit var binding: RvAddressItemBinding
    var addressPosition : Int = 0

    inner class ItemViewHolder(private var binding : RvAddressItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: AddressData) {
            binding.tvName.text = data.name
            binding.tvAddress.text = data.address
            binding.tvPhoneNumber.text = data.phoneNumber

            // 수정 눌렀을 때
            binding.tvEditAddress.setOnClickListener {
                Intent(context, ModifyAddressActivity::class.java).apply {
                    putExtra("name",data.name)
                    putExtra("address",data.address_first)
                    putExtra("phoneNumber",data.phoneNumber)
                    putExtra("userIdx",data.userIdx)
                    putExtra("addressDesc",data.addressDesc)
                    putExtra("addressPosition",data.addressIdx)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

            // 삭제 눌렀을 때
            binding.tvDeleteAddress.setOnClickListener {
                val dialog = DeleteAddressActivity(context, data.addressIdx)

                // Dialog 사이즈 조절 하기
//                val params: ViewGroup.LayoutParams = dialog.getWindow().getAttributes()
//                params.width = ViewGroup.LayoutParams.MATCH_PARENT
//                params.height = ViewGroup.LayoutParams.MATCH_PARENT
//                dialog.getWindow().setAttributes(params as WindowManager.LayoutParams)

//                dialog.window.setLayout(700, WindowManager.LayoutParams.WRAP_CONTENT)
                dialog.showDialog()

//                val dialogView = layoutInflater.inflate(R.layout.main_dialog, null)
//                val alertDialog = AlertDialog.Builder(mContext).create()
//                alertDialog.setView(dialogView)
//                alertDialog.show()
//                alertDialog.window.setBackgroundDrawableResource(R.drawable.main_popup_background)
//                alertDialog.window.setLayout(700, WindowManager.LayoutParams.WRAP_CONTENT)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // viewholder를 생성하는 부분
        binding = RvAddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    // 데이터를 그려주게되는 함수
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        addressPosition=position
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}