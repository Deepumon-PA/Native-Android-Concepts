package com.deepu.z_learn

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.activity.*
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.viewinterop.AndroidView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deepu.myandroidapp.BuildConfig
import com.deepu.myandroidapp.R
import com.deepu.myandroidapp.databinding.ActivityLearnBinding
import com.deepu.z_learn.eventBus.EventBusCustomMessageEvent
import com.deepu.z_learn.eventBus.EventBusDemoActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.math.BigInteger
import java.security.KeyStore
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec


class LearnActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LearnActivityTAG"
    }

    //demo for securing keys from version control with the help of google secrets gradle plugin
    //put the key inside local.properties, add secrets gradle plugin, rebuild/make project. good to go
    val mySecretKeySecuredFromVersionControl = BuildConfig.MY_SECRET_KEY

    //preference datastore
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "myPrefDataStore")

    lateinit var binding: ActivityLearnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        Log.d(TAG, "inside onCreate of LearnActivity")

        binding = ActivityLearnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //-------------------------------------compose <--> xml migration---------------------------
        //jetpack compose view inside xml
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed) // compose view will be destroyed when fragment finishes

            setContent {

                Column() {
                    Text(text = "This is a compose text")

                    AndroidView(
                        factory = {
                            TextView(it)
                        }
                    ) {

                        it.apply {
                            text = "This is an xml textview inside a compose view"
                            setTextColor(Color.RED)
                            textSize = 20f
                            gravity = Gravity.CENTER
                        }


                    }

                }
                //adding an xml view inside a compose view

            }
        }

        val learnViewModel: LearnViewModel by viewModels()

        //LiveData
        //setValue() change values inside main thread, postValue() change value inside any other background thread
        //live data is lifecycle aware as compared to rxjava and other observable patterns
        //live data is asynchronous by default
        binding.liveData.setOnClickListener {
            learnViewModel.triggerLiveData()
            startActivity(Intent(this, EventBusDemoActivity::class.java))

        }
        learnViewModel.liveData.observe(this, Observer {
            binding.txtLiveData.text = it
        })

        // StateFlow(HOT)
        //holds sate
        binding.stateFlow.setOnClickListener {
            learnViewModel.triggerStateFlow()
        }

        lifecycleScope.launchWhenStarted {  //always use this: launched only when the launching  life cycle is in start state
            learnViewModel.stateFlow.collectLatest {
                binding.txtStateFlow.text = it
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) { //will launch a new coroutines every time the coroutine is in a STARTED state
                // otherwise launch itself will launch even if the view is not visible which will lead to app crash

            }
        }

        //Flow (COLD)
        //cold since the flow will only start emitting when there is a collector
        //simple flow doesn't contain state, so if you rotate the screen it won't preserve the state and it will go back to it's previous state
        binding.flow.setOnClickListener {
            lifecycleScope.launch {
                learnViewModel.triggerFlow().collectLatest {
                    binding.txtFlow.text = it
                }
            }
        }


        //sharedFlow (HOT)
        //mostly for one time events
        //if we are showing a toast, it wont show again if the view is recreated(eg: rotated), since the event occurs only once
        binding.sharedFlow.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                learnViewModel.sharedFlow.collectLatest {

                }
            }
        }

        //summary

        //live data is not used now , use it only in java projects since java doesn't support flows

        //use state flow when you want to hold a state
        //use flow when you want to repeatedly emit some values
        //use sharedFlow for one time events (like showing toasts when an a network call result occurs)


        //when using compose you can use compose states and these flows combined together based on the use case

        //launchWhenStarted (launches when lifecycle is in started state)should be used for state holding flows... but for a simple flow launch is all we need
        //collectLatest will collect the latest emission and cancel all the previous ones

        //---------------------------------------Deep dive into kotlin flows---------------------------------------------------------------------------------


        // Security in android
        // MD5(Message Digest Algorithm) hashing of a string
        protectMySecretKey("I have two legs")


        //---------------Green robots Event Bus---------------------------------------------------
        //Low coupling:
        //Event Bus is a singleton in itself
        //events will always occur on the UI thread

        //Steps for using it
        //1. Add the dependency
        //2. Define message class
        //3. Register a class to listen to events
        EventBus.getDefault()
            .register(this)//this class/Activity is registered to listen to events, getDefault: will return a singleton instance of eventbus
        //4. Subscribe to those events
    }

    @Subscribe
    fun onEvent(event: EventBusCustomMessageEvent) {
        Toast.makeText(this, "Event triggered",Toast.LENGTH_LONG).show()
        Log.d("EventBusTestLog", "Event fired says:  ${event.eventMessage}")
    }


    //usage of keystore to save and protect sensitive keys
    private fun protectMySecretKey(sensitiveKey: String) {

        var keyAlias = "secret fact"

        var keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            "AndroidKeyStore"
        ) //Algorithm used is AES and the provider of the key is AndroidKeystore
        var keyGenParameterSpec = KeyGenParameterSpec.Builder(
            keyAlias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE) //padding simply means adding random bits or  0s (Electronic code blocks(ECB)) to the key before encryption
            .build()


        //encryption
        keyGenerator.init(keyGenParameterSpec)
        var secretKey = keyGenerator.generateKey()

        var cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        var iv = cipher.iv
        var textToEncrypt = "this is my text to encrypt"
        var encryption = cipher.doFinal(textToEncrypt.toByteArray())


        //decryption
        val keystore = KeyStore.getInstance("AndroidKeyStore")
        keystore.load(null)


        val fetchedSecretKeyEntry = keystore.getEntry(keyAlias, null) as KeyStore.SecretKeyEntry

        var fetchedSecretKey = fetchedSecretKeyEntry.secretKey

        var deCipher = Cipher.getInstance("AES/GCM/NoPadding")
        var gcmParameterSpec = GCMParameterSpec(128, iv)
        deCipher.init(Cipher.DECRYPT_MODE, fetchedSecretKey, gcmParameterSpec)

        var decryptedData = deCipher.doFinal(encryption)

        var unencryptedString = String(encryption, Charsets.UTF_8)

    }


    private fun getMD5Hash(input: String): String {

        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')

        /*  var msgDigest = MessageDigest.getInstance("MD5")
          msgDigest.update(input.toByte())
          var digestArray = msgDigest.digest()

          var stringBuilder = StringBuilder()

          for(mByte in digestArray){

              var mString = Integer.toHexString(0xFF & mByte)

          }*/
    }

    //-----------------------------RxJava--------------------------------------------
    //components: observables , observers and subscribers, operators

    //RxAndroid: provides a scheduler to  schedule tasks on the main thread or any other looper
    //



}