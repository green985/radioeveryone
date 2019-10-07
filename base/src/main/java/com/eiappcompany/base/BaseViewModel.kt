package com.eiappcompany.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.eiappcompany.base.util.helper.SingleLiveEvent
import java.util.*
import javax.inject.Inject

/**
Created by EiAppCompany
04-10-2019
17:12
 **/

abstract class BaseViewModel : AndroidViewModel(Application()) {
    /*
     val disposable = CompositeDisposable()

     val load = SingleLiveEvent<Boolean>()
     val exception = SingleLiveEvent<ErrorActionModel>()
     val errorMessageResponse = SingleLiveEvent<String>()

     val context: Context by lazy { getApplication<Application>().applicationContext }


     override fun onCleared() {
         disposable.clear()
         super.onCleared()
     }

     fun <T> Observable<T>.magicSubscribe(
         liveData: MutableLiveData<T>?,
         getResponse: ((T) -> Unit)? = null
     ) {
         subscribe(
             {
                 if (getResponse == null) {
                     liveData!!.postValue(it)
                 } else {
                     getResponse(it)
                 }
             }, {
                 errorMessageResponse.postValue(it.message)
             }
         ).addTo(disposable)
     }

  */

}


