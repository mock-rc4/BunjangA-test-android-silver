package com.example.risingtest.src.main.home.recommend

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentChildHomeRecommendBinding
import com.example.risingtest.src.main.home.recommend.models.RecommendResponse

data class ProductData(var product_name : String, var product_price : String,
                       var product_img : String, var address : String, var time : String, var idx : Int, var productLike : String, var safety : String)

class RecommendFragment : BaseFragment<FragmentChildHomeRecommendBinding>(FragmentChildHomeRecommendBinding::bind, R.layout.fragment_child_home_recommend), RecommendFragmentView {

    private lateinit var recyclerViewAdapter: RecommendRecyclerViewAdapter
    var dataList = ArrayList<ProductData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            initBestSellerRecycelrView()
        }, 500)

        RecommendService(this).tryRecommendProducts()

    }

    fun initBestSellerRecycelrView() {
        recyclerViewAdapter = RecommendRecyclerViewAdapter(this.requireActivity(),dataList)
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

    override fun onGetRecommendSuccess(response: RecommendResponse) {
        var i = 0
        var j = 0
        for (list in response.result!!.listIterator()) {
            if(i==0){

            }else {
                for(data in list.listIterator()){
                    for (index in list.listIterator(j)) {
                        dataList.add(ProductData(list.get(j).productName.toString(),list.get(j).price.toString(), list.get(j).imageUrl.toString(), list.get(j).directtrans.toString(),
                            list.get(j).createAt.toString(), list.get(j).idx!!, list.get(j).productLike.toString(), list.get(j).saftyPay.toString()))
                        Log.d("data",index.toString())
                        j++
                    }
                }
            }
            i++
        }


    }

    override fun onGetRecommendFailure(message: String) {
    }
}