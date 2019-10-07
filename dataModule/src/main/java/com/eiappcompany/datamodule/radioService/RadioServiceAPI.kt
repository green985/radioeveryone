package com.eiappcompany.datamodule.radioService

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.POST

/**
Created by EiAppCompany
07-10-2019
15:20
 **/

interface RadioServiceAPI {

    @POST("/kia-login")
    fun login(): Observable<Response<Any>>


}