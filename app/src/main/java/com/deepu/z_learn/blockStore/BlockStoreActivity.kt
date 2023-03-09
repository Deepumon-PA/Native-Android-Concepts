package com.deepu.z_learn.blockStore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.blockstore.Blockstore
import com.google.android.gms.auth.blockstore.BlockstoreClient
import com.google.android.gms.auth.blockstore.RetrieveBytesRequest
import com.google.android.gms.auth.blockstore.RetrieveBytesResponse
import com.google.android.gms.auth.blockstore.StoreBytesData
import java.util.Arrays

/**
 * BlockStore
 */

//Allows to store key-pare values, allows you to save up to 64 times more date
//Allows us to pair byte array packets with a key

//Why blockStore ?
// Immediately get user preferences/session data on new device
// and that the data is encrypted end to end
// seamless sign in experiences for existing users

//when using the app on a new device, user can go for the following backup options
// 1. Wired Restore: Device to Device transfer of data
// 2. Cloud Restore: Getting backup data from the cloud
// the data that is stored in block store will be immediately available on the device when selecting the above options

//Advantages of using block store
// Locally store data outside of applications partition, ie, data is still persistent when the application is reinstalled
// Backup to cloud

/** use Block store mostly for session and user preference scenarios **/

class BlockStoreActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val blockStoreClient = Blockstore.getClient(this)

        val dummyBytes = byteArrayOf(1, 2, 3, 4)

        val key = "com.deepu.z_learn.blockStore.key"


        setContent {

            MaterialTheme{

                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                ){

                    Button(onClick = {
                        storeDataInBlockStore(dummyBytes, key, blockStoreClient)
                    }) {
                        Text(text = "Store Block store data")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = {
                        retrieveFromBlockStore(blockStoreClient, key)
                    }) {
                        Text(text = "Retrieve Block store data")
                    }

                }
            }

        }
    }


    fun storeDataInBlockStore(dummyBytes: ByteArray, key: String, blockStoreClient: BlockstoreClient ){

        val storeRequest1: StoreBytesData = StoreBytesData.Builder()
            .setBytes(dummyBytes)
            .setKey(key)
            . build()

        blockStoreClient.storeBytes(storeRequest1)
            .addOnSuccessListener { result: Int -> Log.d("blockStoreResult", "stored $result bytes")  }
            .addOnFailureListener{ e-> Log.d("blockStoreResult", "failed to store bytes")}

    }

    fun retrieveFromBlockStore(blockStoreClient: BlockstoreClient, key1: String){

        val requestedKeys = Arrays.asList(key1)

        val retrieveRequest = RetrieveBytesRequest.Builder()
            .setKeys(requestedKeys)
            .build()

        blockStoreClient.retrieveBytes(retrieveRequest)
            .addOnSuccessListener { result: RetrieveBytesResponse ->
                val blockStoreDataMap = result.blockstoreDataMap

                for((key, value) in blockStoreDataMap){
                    Log.d("blockStoreResult", String.format("Retrieved bytes %s associated with key %s is. ", String(value.bytes), key))
                }

            }
            .addOnFailureListener{ e: Exception? ->
                Log.d("blockStoreResult", "failed to store bytes $e")
            }
    }
}