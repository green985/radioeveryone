package com.eiappcompany.radioeveryone.ui.main

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.eiappcompany.base.BaseViewModel
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.datamodule.repositories.ExampleRepository
import com.eiappcompany.datamodule.repositories.LoginResponseObject
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
        repository.login().magicSubscribe(loginResult)

    }

    fun doLogin() {
        repository.login().magicSubscribe(loginResult)
    }


}