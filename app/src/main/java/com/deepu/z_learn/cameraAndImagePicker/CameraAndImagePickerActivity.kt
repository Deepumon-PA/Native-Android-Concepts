package com.deepu.z_learn.cameraAndImagePicker

import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts


class CameraAndImagePickerActivity: ComponentActivity() {
}

//Old approaches


//for XML project

//Image picker
/*
1.  selectImageFromGallery.launch("image/*")
2.  private val selectImageFromGallery =
    registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val selectedFilePath = FilePath.getPath(requireActivity(), uri)
            val selectedGalleryFile = File(selectedFilePath!!)

            val originalFilePath = selectedGalleryFile.path
            val bitmapOriginal = BitmapFactory.decodeFile(selectedFilePath)

            mainVM.riderProfilePicEncoded = bitmapToBase64(bitmapOriginal)
            binding.imgUpProfPic.setImageBitmap(bitmapOriginal)

        }
    }
*/

//take photo
/*
1.takePhoto.launch()
2.private val takePhoto =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            it?.let {
                binding.imgUpProfPic.setImageBitmap(it)
                mainVM.riderProfilePicEncoded = bitmapToBase64(it)
            }
        }
*/

 */

