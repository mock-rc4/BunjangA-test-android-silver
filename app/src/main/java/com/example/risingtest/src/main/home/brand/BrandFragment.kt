package com.example.risingtest.src.main.home.brand

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentChildHomeBrandBinding
import com.example.risingtest.src.main.home.recommend.*

data class BrandData(var brandName : String, var brandImg : Int)
data class BrandDetailData(var product_name : String, val product_img : Int)

class BrandFragment : BaseFragment<FragmentChildHomeBrandBinding>(FragmentChildHomeBrandBinding::bind, R.layout.fragment_child_home_brand) {

    private lateinit var recyclerViewAdapter: BrandRecyclerViewAdapter
    var dataList = ArrayList<BrandData>()
    var dataDetailList = ArrayList<BrandDetailData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBestSellerRecycelrView()

        dataList.add(BrandData("뉴에라", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("나이키", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))
        dataList.add(BrandData("뉴발란스", R.drawable.ic_home_tab_brand_logo))

        dataDetailList.add(BrandDetailData("뉴에라 볼캡 카키", R.drawable.ic_brand_detail_product_name))
        dataDetailList.add(BrandDetailData("뉴에라 볼캡 카키", R.drawable.ic_brand_detail_product_name))
        dataDetailList.add(BrandDetailData("뉴에라 볼캡 카키", R.drawable.ic_brand_detail_product_name))
        dataDetailList.add(BrandDetailData("뉴에라 볼캡 카키", R.drawable.ic_brand_detail_product_name))
        dataDetailList.add(BrandDetailData("뉴에라 볼캡 카키", R.drawable.ic_brand_detail_product_name))
        dataDetailList.add(BrandDetailData("뉴에라 볼캡 카키", R.drawable.ic_brand_detail_product_name))
        dataDetailList.add(BrandDetailData("뉴에라 볼캡 카키", R.drawable.ic_brand_detail_product_name))



    }

    fun initBestSellerRecycelrView() {
        recyclerViewAdapter = BrandRecyclerViewAdapter(this.requireActivity(),dataList,dataDetailList)
        Log.d("dkdkdk",dataList.toString())
        binding.rvHomeBrandItem.adapter = recyclerViewAdapter
        binding.rvHomeBrandItem.layoutManager = LinearLayoutManager(context)
        binding.rvHomeBrandItem.addItemDecoration(ItemBrandDecoration(this.requireActivity()))

    }

    fun newInstant() : BrandFragment {
        val args = Bundle()
        val frag = BrandFragment()
        frag.arguments=args
        return frag
    }
}