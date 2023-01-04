package com.deepu.z_learn.translateText

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deepu.myandroidapp.R
import com.deepu.myandroidapp.databinding.ActivityTranslateTextBinding
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.*
import org.koin.android.ext.android.bind

/**
 * google ML kit to translate text
 */
class TranslateTextActivity: AppCompatActivity() {

    lateinit var binding: ActivityTranslateTextBinding
    lateinit var  englishGermanTranslator: Translator
    lateinit var options: TranslatorOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslateTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTranslationEnvironment()

        manageTranslationModel()

        //-----------------editText addTextChangedListener-----------------------
        binding.edTxtInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.btnTranslate.setOnClickListener {
            translateText(binding.edTxtInput.text.toString())
        }
    }

    fun translateText(text: String){

        englishGermanTranslator.translate(text)
            .addOnSuccessListener { translatedText ->
                binding.txtTranslation.text = translatedText
            }.addOnFailureListener { excepion ->
                val exceptionText = "exception ${excepion.message}"
                   binding.txtTranslation.text = exceptionText
            }

    }


    fun setUpTranslationEnvironment(){
        options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.GERMAN)
            .build()

        englishGermanTranslator = Translation.getClient(options)


        val conditions = DownloadConditions.Builder()
            .build()

        binding.txtTranslation.text = "Downloading translation model........"

        englishGermanTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {

                binding.txtTranslation.text = "Model download Success"

            }
            .addOnFailureListener { exception ->
                binding.txtTranslation.text = "Model download failed"


            }
    }

    fun manageTranslationModel(){
        val modelManager = RemoteModelManager.getInstance()

        modelManager.getDownloadedModels(TranslateRemoteModel::class.java)
            .addOnSuccessListener { models ->

                val germanModel = TranslateRemoteModel.Builder(TranslateLanguage.GERMAN).build()

                if(models.contains(germanModel)){
                    Toast.makeText(this, "german model found",Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this, "couldn't find the model",Toast.LENGTH_SHORT).show()

                }

                //to delete a model
                modelManager.deleteDownloadedModel(germanModel)
                    .addOnSuccessListener {
                        // Model deleted.
                    }
                    .addOnFailureListener {
                        // Error.
                    }

                // Download the French model.
                val frenchModel = TranslateRemoteModel.Builder(TranslateLanguage.FRENCH).build()
                val conditions = DownloadConditions.Builder()
                    .requireWifi()
                    .build()
                modelManager.download(frenchModel, conditions)
                    .addOnSuccessListener {
                        // Model downloaded.
                    }
                    .addOnFailureListener {
                        // Error.
                    }


            }
            .addOnFailureListener{

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        closeTranslator()
    }


    fun closeTranslator(){
            val translator = Translation.getClient(options)
        lifecycle.addObserver(translator)
        englishGermanTranslator.close()

    }

}