package com.eiappcompany.datamodule.radioService

import com.eiappcompany.datamodule.repositories.GenericResponse
import com.eiappcompany.datamodule.repositories.LoginResponseObject
import com.eiappcompany.datamodule.repositories.radioModels.RadioResponseModel
import com.eiappcompany.datamodule.repositories.radioModels.RadioUrlRequestModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
Created by EiAppCompany
07-10-2019
15:20
 **/

interface RadioServiceAPI {

    @POST("/rgw_st_stream.php")
    fun getRadioUrlData(@Body requestModel: RadioUrlRequestModel): Observable<Response<GenericResponse<RadioResponseModel>>>
}