package com.eiappcompany.radioeveryone.ui.main

import androidx.lifecycle.MutableLiveData
import com.eiappcompany.base.BaseViewModel
import com.eiappcompany.base.crowler.crowlerModel.RadioDataModelCrowler
import com.eiappcompany.base.util.ext.HelperExt
import com.eiappcompany.base.util.helper.extensions.addTo
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.datamodule.repositories.LoginResponseObject
import com.eiappcompany.datamodule.repositories.RadioDataRepository
import com.eiappcompany.exoplayermodule.exoPlayerDi.radio.RadioClass
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioDataModel
import timber.log.Timber
import javax.inject.Inject

/**
Created by EiAppCompany
04-10-2019
16:48
 **/

class MainVM @Inject constructor(
    val repository: RadioDataRepository
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


    fun startRadio(radioData: RadioDataModelCrowler) {
        repository.getRadioStreamData(radioData.radioKey).subscribe({
            if (it.data != null) {
                var tmpDataModel = RadioDataModel()
                if (it.data!!.radioGenericModel.streamModel.isEmpty()) {
                    //TODO
                    Timber.d("doError")
                }
                tmpDataModel.radioStreamUrl = it.data!!.radioGenericModel.streamModel[0].streamLink
                tmpDataModel.radioName = radioData.radioName
                radioExo.initRadioDataModel(tmpDataModel)

            } else {
                Timber.d("doError2")
            }
        }, {
            Timber.d("doError%s", it.message)

        }).addTo(disposable)
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