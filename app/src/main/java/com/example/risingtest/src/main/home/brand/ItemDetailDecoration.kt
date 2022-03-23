package com.example.risingtest.src.main.home.brand

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class ItemDetailDecoration(context: Context) : ItemDecoration() {
    private val size10: Int
    private val size5: Int
    private val size20: Int
    private val size12: Int
    private val size0: Int

    // dp -> pixel 단위로 변경
    private fun dpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.getResources().getDisplayMetrics()
        )
            .toInt()
    }

    init {
        size10 = dpToPx(context, 10)
        size12 = dpToPx(context, 12)
        size5 = dpToPx(context, 5)
        size20 = dpToPx(context,20)
        size0 = dpToPx(context,0)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        outRect.top = size10
        outRect.right = size20
        outRect.bottom = size10

        // 첫번째 아이템과 마지막 아이템
        if(position != itemCount-1){
            outRect.right = size12
        } else {
            outRect.right = size20
        }

    }
}