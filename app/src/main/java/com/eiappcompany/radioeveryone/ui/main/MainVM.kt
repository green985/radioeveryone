package com.eiappcompany.radioeveryone.ui.main

import androidx.lifecycle.MutableLiveData
import com.eiappcompany.base.BaseViewModel
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.datamodule.repositories.ExampleRepository
import com.eiappcompany.datamodule.repositories.LoginResponseObject
import com.eiappcompany.exoplayermodule.exoPlayerDi.radio.RadioClass
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioDataModel
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

    @Inject
    lateinit var radioExo: RadioClass


    init {
        //repository.login().magicSubscribe(loginResult)

    }

    fun doLogin() {
        repository.login().magicSubscribe(loginResult)
    }

    fun startRadio() {
        var radioDataModel = RadioDataModel()
        radioDataModel.radioStreamUrl = "http://yayin.radyohayalfm.net:8050"
        radioExo.initRadioDataModel(radioDataModel)
    }

    fun startdifferentRadioRadio() {
        var radioDataModel = RadioDataModel()
        radioDataModel.radioStreamUrl = "http://streaming.radio.co/s0ce169aac/listen"
        radioExo.initRadioDataModel(radioDataModel)
    }

    fun stopRadio() {
        radioExo.stopRadio()

    }

    fun destroyRadio() {
        radioExo.destroyRadio()

    }


}