package com.eiappcompany.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.eiappcompany.base.util.helper.SingleLiveEvent
import com.eiappcompany.base.util.helper.extensions.addTo
import com.eiappcompany.base.util.viewState.ViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
Created by EiAppCompany
04-10-2019
17:12
 **/

abstract class BaseViewModel : AndroidViewModel(Application()) {

    val disposable = CompositeDisposable()
    /*
        val load = SingleLiveEvent<Boolean>()
        val exception = SingleLiveEvent<ErrorActionModel>()

        val context: Context by lazy { getApplication<Application>().applicationContext }


     */
    val errorMessageResponse = SingleLiveEvent<String>()


    fun <T> Observable<ViewState<T>>.magicSubscribe(
        liveData: MutableLiveData<ViewState<T>>?,
        getResponse: ((ViewState<T>) -> Unit)? = null
    ) {
        this.startWith(ViewState.loading(null))
            .subscribe(
                {
                    if (getResponse == null) {
                        liveData!!.postValue(it)
                    } else {
                        getResponse(it)
                    }
                }, {
                    if (it.message == null) {
                        liveData?.value = ViewState.error("Bir hata oluştu.")
                    } else {
                        liveData?.value = ViewState.error(it.message!!)
                    }
                }
            ).addTo(disposable)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}


