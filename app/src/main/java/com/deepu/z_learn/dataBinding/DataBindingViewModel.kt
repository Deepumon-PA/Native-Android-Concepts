package com.deepu.z_learn.dataBinding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deepu.z_learn.dataBinding.databindingModels.UserModel

class DataBindingViewModel: ViewModel() {

    companion object{
        private const val TAG = "DataBindingViewModel"
    }

    init {
        initializeUserModel()
    }

//    private lateinit var _userModel:MutableLiveData<UserModel>
    lateinit var  userModel:MutableLiveData<UserModel>

    fun initializeUserModel(){
        userModel = MutableLiveData()
        userModel.value = UserModel()
//         _userModel.value = UserModel()
    }

    fun changeUserModel(luckyNumber: Int){
         userModel.value?.luckyNumber = luckyNumber
          Log.d(TAG, "changed value ${userModel.value?.luckyNumber}")
    }

}