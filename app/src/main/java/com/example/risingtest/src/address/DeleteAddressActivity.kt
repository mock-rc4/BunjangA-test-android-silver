package com.example.risingtest.src.address

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.DialogDeleteAddressBinding
import com.example.risingtest.src.address.models.AddressDeleteResponse

class DeleteAddressActivity(context: Context, addrxessIdx : String) : BaseActivity<DialogDeleteAddressBinding>(DialogDeleteAddressBinding::inflate), DeleteAddressActivityView {

    private val dialog = Dialog(context)
    private val mContext = context
    private var addressIdx = Integer.parseInt(addrxessIdx)

    fun showDialog(){
        dialog.setContentView(R.layout.dialog_delete_address)

//        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
//        dialog.setCanceledOnTouchOutside(true)
//        dialog.setCancelable(true)
//        dialog.show()

        val lp = WindowManager.LayoutParams()
        lp.width=850
        lp.height=580
        dialog.window!!.setLayout(lp.width, lp.height)
        // 모서리 둥글
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val btnYes = dialog.findViewById<Button>(R.id.btn_yes)
        val btnNo = dialog.findViewById<Button>(R.id.btn_no)

        Log.d("addressIdx", addressIdx.toString())

        btnYes.setOnClickListener {
            DeleteAddressService(this).tryDeleteAddress(addressIdx)
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onDeleteAddressSuccess(response: AddressDeleteResponse) {
        if(response.code==1000){
            Toast.makeText(mContext, "주소가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
            Intent(mContext, AddressActivity::class.java).apply {
            }.run { mContext.startActivity(this) }
            dialog.dismiss()
        }
    }

    override fun onDeleteAddressFailure(message: String) {
    }


}