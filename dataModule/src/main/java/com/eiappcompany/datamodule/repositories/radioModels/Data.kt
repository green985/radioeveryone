package com.eiappcompany.datamodule.repositories.radioModels


import com.google.gson.annotations.SerializedName

data class RadioGenericModel(
    @SerializedName("Data")
    var streamModel: List<RadioStreamModel> = listOf(),
    @SerializedName("ErrorCode")
    var errorCode: Int = 0,
    @SerializedName("ErrorMessage")
    var errorMessage: String = ""
)