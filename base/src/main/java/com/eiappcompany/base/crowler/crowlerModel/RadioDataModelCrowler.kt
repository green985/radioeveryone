package com.eiappcompany.base.crowler.crowlerModel


import com.google.gson.annotations.SerializedName

data class RadioDataModelCrowler(
        @SerializedName("radioCategoryList")
        var radioCategoryList: List<String> = listOf(),
        @SerializedName("radioImageUrl")
        var radioImageUrl: String = "",
        @SerializedName("radioKey")
        var radioKey: String = "",
        @SerializedName("radioLanguage")
        var radioLanguage: String = "",
        @SerializedName("radioLanguageCode")
        var radioLanguageCode: String = "",
        @SerializedName("radioListenNumber")
        var radioListenNumber: String = "",
        @SerializedName("radioName")
        var radioName: String = "",
        @SerializedName("radioUrl")
        var radioUrl: String = ""
)