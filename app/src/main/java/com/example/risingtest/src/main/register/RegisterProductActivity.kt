package com.example.risingtest.src.main.register

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityProductRegisterBinding

class RegisterProductActivity : BaseActivity<ActivityProductRegisterBinding>(ActivityProductRegisterBinding::inflate) {

    private var imgUrlList : ArrayList<Uri> = arrayListOf()
    val PERM_STORAGE = 9
    val PERM_CAMERA = 10

    val REQ_CAMERA = 11
    val REQ_GALLERY = 12
    val REQUEST_CODE_CATEGORY_ACTIVITY=7777


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. 공용 저장소 권한이 있는지 확인
        requirePermission(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), PERM_STORAGE)

    }

    // 카메라 열기
    fun openCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQ_CAMERA)
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent,REQ_GALLERY)
    }

    fun initViews() {
        binding.clSelectPhoto.setOnClickListener {
//            requirePermission(arrayOf(android.Manifest.permission.CAMERA), PERM_CAMERA)
            openGallery()
        }
    }

    // 권한이 승인됐다
    fun permissionGranted(requestCode: Int) {
        when(requestCode) {
            PERM_STORAGE -> initViews()
            PERM_CAMERA -> openCamera()
        }
    }

    // 권한이 거절됐다면
    fun permissionDenied(requestCode: Int) {
        when(requestCode) {
            PERM_STORAGE -> {
                Toast.makeText(this, "공용 저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            PERM_CAMERA -> {
                Toast.makeText(this, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
////        Toast.makeText(this,"게시글 작성 onActivity 실행", Toast.LENGTH_LONG).show()
//        if(requestCode == REQ_CAMERA){
//            // 카메라로 찍고 다시 돌아을 때 실행하는 함수
//            if(resultCode== RESULT_OK){
//                //카메라는 extra로 데이터를 꺼낸다.
//                    val uri = data?.data
//                if(uri != null){
//                    binding.
//                }
//                val bitmap = data?.extras?.get("data") as Bitmap   // 미리보기 이미지
//                binding.imgSelectPhotoPreview.setImageBitmap(bitmap)
//                binding.imgSelectPhotoPreview.isVisible = true
//            }
//        } else if(requestCode == REQ_GALLERY){
//            if(resultCode== RESULT_OK){
//                //사진은 URL 형태로 data에 이미지가 담기게 된다.
//                data?.data?.let { uri->
//                    binding.imgSelectPhotoPreview.setImageURI(uri)
//                    // URI는 저장과정이 필요없기때문에 카메라와 달리 uri로 바로 출력
//                    binding.imgSelectPhotoPreview.isVisible = true
//                    // 사진 선택 개수를 반영해서 text바꾸기
////                    binding.tvTheNumerOfPhoto.setText()
//                }
//            }
//        }
//    }

    fun requirePermission(permissions:Array<String>, requestCode: Int){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            permissionGranted(requestCode)
        }else {
            // 권한이 없으면 권한 요청 -> 팝업
            ActivityCompat.requestPermissions(this, permissions, requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.all { it == PackageManager.PERMISSION_GRANTED}) {
            permissionGranted(requestCode)
        }else {
            permissionDenied(requestCode)
        }
    }

}