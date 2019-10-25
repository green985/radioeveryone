package com.eiappcompany.datamodule.repositories.radioModels


import com.google.gson.annotations.SerializedName

data class RadioResponseModel(
    @SerializedName("data")
    var radioGenericModel: RadioGenericModel = RadioGenericModel(),
    @SerializedName("http_response_code")
    var httpResponseCode: Int = 0,
    @SerializedName("http_response_message")
    var httpResponseMessage: String = ""
)