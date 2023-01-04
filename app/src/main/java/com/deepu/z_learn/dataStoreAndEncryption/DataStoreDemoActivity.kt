package com.deepu.z_learn.dataStoreAndEncryption

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import ch.qos.logback.core.spi.LifeCycle
import com.deepu.myandroidapp.databinding.ActivityDataStoreDemoBinding
import com.deepu.z_learn.DataStoreManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class DataStoreDemoActivity: Activity() {

    lateinit var binding: ActivityDataStoreDemoBinding
    lateinit var dataStoreManager: DataStoreManager

    lateinit var myCoroutineScope: CoroutineScope
    lateinit var lastItem: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataStoreDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCoroutineScope = CoroutineScope(Dispatchers.IO)

        dataStoreManager = DataStoreManager(this)

        binding.btnEncrypt.setOnClickListener {
            encryptAndStoreInDataStore(binding.edDataTxt.text.toString())
        }

    }

    fun encryptAndStoreInDataStore(text: String){

        var job = myCoroutineScope.launch {
            dataStoreManager.writeEncryptedText(text)
        }

        decryptAndRetrieveFromDataStore()
    }

    fun decryptAndRetrieveFromDataStore(){


        myCoroutineScope.launch {

            dataStoreManager.getEncryptedText().catch { e->
                  e.printStackTrace()
            }.collect {
                   withContext(Dispatchers.Main){
                       lastItem = it
                       Toast.makeText(this@DataStoreDemoActivity, "text is: $lastItem", Toast.LENGTH_SHORT).show()

                   }
            }

        }
    }

}