package com.eiappcompany.datamodule.radioService

import com.eiappcompany.datamodule.repositories.GenericResponse
import com.eiappcompany.datamodule.repositories.LoginRequest
import com.eiappcompany.datamodule.repositories.LoginResponseObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
Created by EiAppCompany
07-10-2019
15:20
 **/

interface RadioServiceAPI {

    @POST("/kia-login")
    fun login(@Body request: LoginRequest): Observable<Response<GenericResponse<LoginResponseObject>>>


}