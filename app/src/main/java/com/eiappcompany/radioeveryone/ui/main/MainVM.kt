package com.eiappcompany.radioeveryone.ui.main

import com.eiappcompany.base.BaseViewModel
import com.eiappcompany.datamodule.repositories.ExampleRepository
import timber.log.Timber
import javax.inject.Inject

/**
Created by EiAppCompany
04-10-2019
16:48
 **/

class MainVM @Inject constructor(
    val provideDeneme: String,
    val repository: ExampleRepository
) : BaseViewModel() {

    init {
        Timber.d("==" + provideDeneme)
        repository.login().subscribe {
            Timber.d("subcribe ettim")

        }
        repository.deneme()
    }

    var k = "kısdhıaubdhuıahnsd"
}