package com.example.risingtest.src.main.home.brand

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class ItemBrandDecoration(context: Context) : ItemDecoration() {
    private val size10: Int
    private val size5: Int
    private val size20: Int
    private val size30: Int
    private val size7: Int
    private val size15: Int

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
        size5 = dpToPx(context, 5)
        size20 = dpToPx(context,20)
        size7 = dpToPx(context,7)
        size15 = dpToPx(context,15)
        size30 = dpToPx(context,30)
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

        //상하 설정
        if (position == 0) {
            // 첫번 째 줄 아이템
            outRect.top = size15
            outRect.bottom = size20
        } else {
            outRect.top = size10
            outRect.bottom = size30
        }

        // spanIndex = 0 -> 왼쪽
        // spanIndex = 1 -> 오른쪽
//        val lp = view.getLayoutParams() as GridLayoutManager.LayoutParams
//        val spanIndex = lp.spanIndex
//        if (spanIndex == 0) {
//            //왼쪽 아이템
//            outRect.right = size5
//        } else if (spanIndex == 1) {
//            //오른쪽 아이템
//            outRect.left = size7
//        }
    }
}