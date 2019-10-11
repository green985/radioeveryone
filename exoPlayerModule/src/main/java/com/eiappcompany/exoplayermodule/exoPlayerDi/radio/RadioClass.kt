package com.eiappcompany.exoplayermodule.exoPlayerDi.radio

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioDataModel
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import javax.inject.Inject

/**
Created by EiAppCompany
11-10-2019
15:17
 **/

class RadioClass @Inject constructor(
    var simpleExoPlayer: SimpleExoPlayer,
    var hlsMediaSource: HlsMediaSource.Factory,
    var extractorSource: ExtractorMediaSource.Factory
) {

    var radioViewState = MutableLiveData<ViewState<RadioDataModel>>()
    var radioDataModel = RadioDataModel()


    fun initRadioDataModel(radioDataModel: RadioDataModel) {
        if (radioDataModel.radioStreamUrl == radioDataModel.radioStreamUrl) {
            //Fetch same radio, if radio stop start it or do nothing
            if (simpleExoPlayer.playbackState == Player.STATE_READY) {
                if (simpleExoPlayer.playWhenReady) {
                    //Do nothing, radio play
                    return
                } else {
                    //Start radio
                    simpleExoPlayer.playWhenReady = true
                }
            } else {
                startRadio(radioDataModel.radioStreamUrl)
            }
        }
    }

    /**
     *
    errorCode == 0 MediaSource should be HlsMediaSource.Factory
    default == MediaSource ExtractorMediaSource.Factory
     **/
    fun startRadio(streamUrl: String, errorCode: Int = -1) {

        var radioStreamUri = Uri.parse(streamUrl)
        var mediaSource: MediaSource
        if (errorCode == 0) {
            mediaSource = hlsMediaSource.createMediaSource(radioStreamUri)
        } else {
            mediaSource = extractorSource.createMediaSource(radioStreamUri)
        }

        simpleExoPlayer.prepare(mediaSource)
        simpleExoPlayer.playWhenReady = true
    }


}