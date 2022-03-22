package com.example.risingtest.src.main.home.brand

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentChildHomeBrandBinding
import com.example.risingtest.src.main.home.recommend.*

data class BrandData(var product_name : String)
data class BrandDetailData(var product_name : String, val product_img : Int)

class BrandFragment : BaseFragment<FragmentChildHomeBrandBinding>(FragmentChildHomeBrandBinding::bind, R.layout.fragment_child_home_brand) {

    private lateinit var recyclerViewAdapter: BrandRecyclerViewAdapter
    var dataList = ArrayList<BrandData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun initBestSellerRecycelrView() {
        recyclerViewAdapter = BrandRecyclerViewAdapter(dataList)
        Log.d("dkdkdk",dataList.toString())
        binding.rvHomeBrandItem.adapter = recyclerViewAdapter
        binding.rvHomeBrandItem.layoutManager = LinearLayoutManager(context)
//        binding.rvHomeBrandItem.layoutManager = GridLayoutManager(context, 2)
//        binding.rvHomeBrandItem.addItemDecoration(ItemDecoration(this.requireActivity()))

    }

    fun newInstant() : BrandFragment {
        val args = Bundle()
        val frag = BrandFragment()
        frag.arguments=args
        return frag
    }
}