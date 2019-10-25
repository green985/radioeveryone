package com.eiappcompany.datamodule.repositories.radioModels


import com.google.gson.annotations.SerializedName

data class RadioStreamModel(
    @SerializedName("stream_bitrate")
    var streamBitrate: String = "",
    @SerializedName("stream_flag")
    var streamFlag: String = "",
    @SerializedName("stream_link")
    var streamLink: String = "",
    @SerializedName("stream_type")
    var streamType: String = ""
)