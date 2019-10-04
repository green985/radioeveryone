package com.eiappcompany.radioeveryone.ui.main

import com.eiappcompany.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

/**
Created by EiAppCompany
04-10-2019
16:48
 **/

class MainVM @Inject constructor(
    val provideDeneme: String
) : BaseViewModel() {

    init {
        Timber.d("==" + provideDeneme)
    }

    var k = "kısdhıaubdhuıahnsd"
}