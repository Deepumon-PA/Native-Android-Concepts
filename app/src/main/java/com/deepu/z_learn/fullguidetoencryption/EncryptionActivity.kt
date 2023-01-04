package com.deepu.z_learn.fullguidetoencryption

import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher

//where we do store keys
//Keystore prevents attackers from extract the keys out of the device
//TEE: Trusted Execution Environment -Manages the keystore,  A separate hardware part that is not part of the android operating system, even if someone has root access to the android operating system, they can't obtain root access to TEE
//
class EncryptionActivity {

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

//    private val encryptCipher = Cipher.getInstance()



    companion object{
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES    //AES, RSA algorithms are there
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }
}