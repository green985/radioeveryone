package com.eiappcompany.radioeveryone.ui.main

import androidx.lifecycle.MutableLiveData
import com.eiappcompany.base.BaseViewModel
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.datamodule.repositories.ExampleRepository
import com.eiappcompany.datamodule.repositories.LoginResponseObject
import timber.log.Timber
import javax.inject.Inject

/**
Created by EiAppCompany
04-10-2019
16:48
 **/

class MainVM @Inject constructor(
    val repository: ExampleRepository
) : BaseViewModel() {

    var loginResult = MutableLiveData<ViewState<LoginResponseObject>>()

    init {
        /*
        repository.login().subscribe({
            loginResult.value = it
        }, {
            loginResult.value = it.message?.let { it1 -> ViewState.error(it1) }
        }).addTo(disposable)


         */
        repository.login().magicSubscribe(loginResult)

    }


}