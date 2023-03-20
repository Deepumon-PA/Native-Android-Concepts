package com.deepu.z_learn.appPerformanceAndSecurityAndPrivacy.appSecurity.fullguideto_encryption_decryption_crypto

import android.os.Bundle
import android.security.keystore.KeyProperties
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.security.KeyStore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle

//where we do store keys
//Keystore prevents attackers from extract the keys out of the device
//TEE: Trusted Execution Environment -Manages the keystore,  A separate hardware part that is not part of the android operating system, even if someone has root access to the android operating system, they can't obtain root access to TEE
//
class EncryptionActivity : ComponentActivity() {

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

//    private val encryptCipher = Cipher.getInstance()


    companion object {
        private const val ALGORITHM =
            KeyProperties.KEY_ALGORITHM_AES    //AES, RSA algorithms are there
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var inputText by remember {
                mutableStateOf("")
            }
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TextField(value = inputText, onValueChange = {
                        inputText = it
                    }, textStyle = TextStyle(
                    ), label = {Text("Enter the string")})
                    Spacer(modifier = Modifier.width(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(onClick = {  }) {
                          Text("Encrypt")
                        }

                        Button(onClick = {  }) {
                            Text("Decrypt")
                        }
                    }

                }
            }
        }
    }
}