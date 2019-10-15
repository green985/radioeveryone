package com.eiappcompany.radioeveryone.ui.main

import androidx.lifecycle.MutableLiveData
import com.eiappcompany.base.BaseViewModel
import com.eiappcompany.base.crowler.crowlerModel.RadioDataModelCrowler
import com.eiappcompany.base.util.ext.HelperExt
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
    var radioListLiveData = MutableLiveData<ViewState<List<RadioDataModelCrowler>>>()

    @Inject
    lateinit var radioExo: RadioClass


    init {
        getRadioList()
    }


    fun getRadioList() {
        val radioString =
                repository.appHelper.getContext().assets.open("radioList.json").bufferedReader()
                        .use { it.readText() }

        if (radioString != "") {
            radioListLiveData.value =
                    ViewState.success(HelperExt.makeListFromJsonString(radioString))
        }
    }


    fun doLogin() {
        repository.login().magicSubscribe(loginResult)
    }

    fun startRadio() {
        var radioDataModel = RadioDataModel()
        radioDataModel.radioStreamUrl = "http://17773.live.streamtheworld.com/JOY_TURK2AAC_SC"
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