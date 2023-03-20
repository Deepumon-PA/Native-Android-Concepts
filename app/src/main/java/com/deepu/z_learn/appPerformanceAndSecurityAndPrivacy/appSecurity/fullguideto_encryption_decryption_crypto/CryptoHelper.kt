package com.deepu.z_learn.appPerformanceAndSecurityAndPrivacy.appSecurity.fullguideto_encryption_decryption_crypto

import java.security.KeyStore

class CryptoManager {
    //Create a keystore Instance
   private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
       load(null)
    }

}