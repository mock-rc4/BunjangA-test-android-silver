package com.example.risingtest.src.main.home.recommend

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentChildHomeRecommendBinding

data class ProductData(var product_name : String, var product_price : String,
                       var product_img : Int, var address : String, var time : String, var idx : Int)

class RecommendFragment : BaseFragment<FragmentChildHomeRecommendBinding>(FragmentChildHomeRecommendBinding::bind, R.layout.fragment_child_home_recommend) {

    private lateinit var recyclerViewAdapter: RecommendRecyclerViewAdapter
    var dataList = ArrayList<ProductData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBestSellerRecycelrView()

        dataList.add(ProductData("cos 코스 퀄티드 미니백ddddddd", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",1))
        dataList.add(ProductData("cos 코스 퀄티드 미니백dddddd", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",2))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",3))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",4))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",5))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",6))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",7))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",8))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",9))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",10))
        dataList.add(ProductData("cos 코스 퀄티드 미니백", "90,000원", R.drawable.ic_product, "부평구 부평4동", "3시간 전",11))

    }

    fun initBestSellerRecycelrView() {
        recyclerViewAdapter = RecommendRecyclerViewAdapter(this.requireActivity(),dataList)
        Log.d("dkdkdk",dataList.toString())
        binding.rvRecommendList.adapter = recyclerViewAdapter
        binding.rvRecommendList.layoutManager = GridLayoutManager(context, 2)
        binding.rvRecommendList.addItemDecoration(ItemDecoration(this.requireActivity()))

    }

    fun newInstant() : RecommendFragment{
        val args = Bundle()
        val frag = RecommendFragment()
        frag.arguments=args
        return frag
    }
}