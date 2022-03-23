package com.example.risingtest.src.main.home.recommend

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R
import com.example.risingtest.src.main.home.brand.BrandData
import com.example.risingtest.src.main.home.brand.BrandDetailData
import com.example.risingtest.src.main.home.brand.BrandDetailRecyclerViewAdapter
import com.example.risingtest.src.main.home.brand.ItemDetailDecoration

class BrandRecyclerViewAdapter(private val context: Context, private val dataList : ArrayList<BrandData>, private val dataDetailList : ArrayList<BrandDetailData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER : Int = 0
    private val TYPE_ITEM : Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(context).inflate(R.layout.header_home_brand, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view =  LayoutInflater.from(context).inflate(R.layout.rv_main_brand_item, parent, false)
                ListViewHolder(view)
            }

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeaderViewHolder -> {
                holder.itemView.setOnClickListener {
                    Log.d("푸터 눌렸을 때","눌렸을때")
                }
            }
            else -> {
                val item = dataList[position-1]
                holder.itemView.apply {
                    val itemViewHolder: ListViewHolder = holder as ListViewHolder
                    itemViewHolder.bind(item)
                }
            }
        }
    }


    // 아이템의 타입을 반환 (position은 0 기반이므로 (전체 갯수 - 1) 일 경우에 Footer 타입 반환)
    override fun getItemViewType(position: Int): Int {
        return when (position) {
//            itemCount -1 -> TYPE_HEADER
            0 -> TYPE_HEADER
            else -> TYPE_ITEM }
    }


    // 아이템의 전체 갯수 + 헤더(1) + 풋터(1) 지금은 풋터만 사용하므로 +1만 해줌.
    override fun getItemCount(): Int = dataList.size+1

    class HeaderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)


    inner class ListViewHolder(layout: View): RecyclerView.ViewHolder(layout) {
        private val brandName: TextView = itemView.findViewById(R.id.tv_brand_name)
        private val brandImg : ImageView = itemView.findViewById(R.id.iv_brand_logo)
        private val recyclerBrandDetail : RecyclerView = itemView.findViewById(R.id.rv_home_brand_item_detail)

        fun bind(item: BrandData) {
            brandName.text = item.brandName
            brandImg.setImageResource(item.brandImg)
            recyclerBrandDetail.adapter = BrandDetailRecyclerViewAdapter(context, dataDetailList)
            recyclerBrandDetail.layoutManager = GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, false)
            recyclerBrandDetail.addItemDecoration(ItemDetailDecoration(context))
        }
    }

}