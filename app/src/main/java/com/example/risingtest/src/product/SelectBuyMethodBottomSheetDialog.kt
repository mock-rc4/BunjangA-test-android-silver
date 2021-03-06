package com.example.risingtest.src.product

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface.OnShowListener
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.risingtest.R
import com.example.risingtest.databinding.FragmentBuyProductBottomSheetBinding
import com.example.risingtest.src.deliveryToBuy.DeliveryToBuyActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SelectBuyMethodBottomSheetDialog(private val productInfo : ArrayList<ProductInfo>) : BottomSheetDialogFragment() {
//class SelectBuyMethodBottomSheetDialog() : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentBuyProductBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuyProductBottomSheetBinding.inflate(inflater, container, false)
        binding.llBuyDelivery.setOnClickListener {
            activity?.let {
                var intent = Intent(context, DeliveryToBuyActivity::class.java)
                intent.putExtra("productIdx", productInfo.get(0).productIdx)
                intent.putExtra("totalPaymentAmount", productInfo.get(0).totalPaymentAmount)
                intent.putExtra("address", productInfo.get(0).address)
                intent.putExtra("safetyTax", productInfo.get(0).safetyTax)
                intent.putExtra("transactionMethod", productInfo.get(0).transactionMethod)
                intent.putExtra("productName", productInfo.get(0).productName)
                intent.putExtra("productImg", productInfo.get(0).productImg)
                startActivity(intent)
                dialog?.dismiss()
            }

        }


        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener(OnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        })
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 35 / 100
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }




}