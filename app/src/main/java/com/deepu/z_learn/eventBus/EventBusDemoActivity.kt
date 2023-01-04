package com.deepu.z_learn.eventBus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deepu.myandroidapp.databinding.ActivityEventBusBinding
import com.deepu.z_learn.LearnActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers.io
import org.greenrobot.eventbus.EventBus

class EventBusDemoActivity : AppCompatActivity() {

    private var _binding: ActivityEventBusBinding? = null
    private val binding get() = _binding!!

    lateinit var disposable: Disposable

    var compositeDisposable: CompositeDisposable = CompositeDisposable() //used to dispose multiple observables and observers


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEventBusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val customMessageEvent = EventBusCustomMessageEvent()



        binding.btnSendMessage.setOnClickListener {
            customMessageEvent.eventMessage = binding.edMessage.text.toString()
            EventBus.getDefault().post(customMessageEvent)
            startActivity(Intent(this, LearnActivity::class.java))
            finish()

        }



        var myStringObserver: Observer<String> = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
                //do some action on subscription
            }

            override fun onNext(t: String) {
                //get the emissions here
            }

            override fun onError(e: Throwable) {
                //catch the errors here if any
            }

            override fun onComplete() {
                //do some action on completion
            }

        }





        compositeDisposable.add(
            getAnotherObserver()
        )




        var myRxObservable: Observable<String> = Observable.just("One", "Two", "Three")



        myRxObservable
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter{ it.length <4 }             // also map() is there
            .subscribeWith(myStringObserver)


    }

    fun getAnotherObserver(): DisposableObserver<String>{
      return object: DisposableObserver<String>() {
          override fun onNext(t: String) {
          }

          override fun onError(e: Throwable) {
          }

          override fun onComplete() {
          }

      }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()

        //or if we are using composite disposable
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}


