package com.example.risingtest.src.login

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface.OnShowListener
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.risingtest.R
import com.example.risingtest.databinding.DialogRegisterBottomSheetBinding
import com.example.risingtest.databinding.FragmentLoginBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PhoneLoginBottomSheetDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding : DialogRegisterBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogRegisterBottomSheetBinding.inflate(inflater, container, false)
        select_telecom()

        return binding.root
    }

    fun select_telecom(){
        binding.selectSKT.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.selectKT.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.selectLGU.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.selectSKTcheap.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
        binding.selectKTcheap.setOnClickListener {
            itemClick(4)
            dialog?.dismiss()
        }
        binding.selectLGUcheap.setOnClickListener {
            itemClick(5)
            dialog?.dismiss()
        }
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
        return getWindowHeight() * 50 / 100
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }




}