package com.deepu.z_learn.dataBinding

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.deepu.myandroidapp.R
import com.deepu.myandroidapp.databinding.ActivityDataBindingBinding
import com.deepu.z_learn.dataBinding.databindingModels.UserModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random


@AndroidEntryPoint
class DataBindingActivity : AppCompatActivity() {

    val dataBindingViewModel: DataBindingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDataBindingBinding =
            DataBindingUtil.setContentView<ActivityDataBindingBinding>(this, R.layout.activity_data_binding)

        binding.user = UserModel()

        dataBindingViewModel.userModel.observe(this) {
            Log.d("deepul","triggered")
            binding.txt3.text = it.luckyNumber.toString()

        }
        //or

        binding.lifecycleOwner = this
        binding.bindViewModel = dataBindingViewModel


        binding.btn1.setOnClickListener {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            //------------------RANDOM NUMBER GENERATION-WITHIN A LIMIT
            dataBindingViewModel.changeUserModel(Random.nextInt((150 - 100) +1) + 100) //random number between 100 and 150
        }


        //--------------DataBinding---------------------
        //evolution: findViewById - ButterKnife - Kotlin Synthetics - DataBinding

        //jetpack library to bind UI components to data sources in declarative format rather than programmatically

        //it reduces boiler plate code

        //two way data binding - code sends data directly to the ui and vice versa


    }

}