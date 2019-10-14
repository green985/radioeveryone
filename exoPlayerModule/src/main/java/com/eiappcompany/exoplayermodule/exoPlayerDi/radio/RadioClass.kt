package com.eiappcompany.exoplayermodule.exoPlayerDi.radio

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioDataModel
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioViewState
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import timber.log.Timber
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
) : Player.EventListener, AnalyticsListener {


    var radioViewState = MutableLiveData<RadioViewState<RadioDataModel>>()
    var radioDataModel = RadioDataModel()

    init {
        simpleExoPlayer.addListener(this)
        simpleExoPlayer.addAnalyticsListener(this)
    }


    fun initRadioDataModel(radioDataModel: RadioDataModel) {
        if (this.radioDataModel.radioStreamUrl == radioDataModel.radioStreamUrl) {
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
        } else {
            startRadio(radioDataModel.radioStreamUrl)
        }
        this.radioDataModel = radioDataModel

    }

    /**
     *
    errorCode == 0 MediaSource should be HlsMediaSource.Factory
    default == MediaSource ExtractorMediaSource.Factory
     **/
    fun startRadio(streamUrl: String, errorCode: Int = -1) {
        Timber.d("startRadio $errorCode")

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

    fun stopRadio() {
        if (simpleExoPlayer.playWhenReady) {
            //simpleExoPlayer.playWhenReady = false
            simpleExoPlayer.stop()
            radioViewState.value = RadioViewState.stop(radioDataModel)
        }
    }

    //Only call notification destroy button
    fun destroyRadio() {
        simpleExoPlayer.apply {
            stop()
            release()
        }
    }

    override fun onVolumeChanged(eventTime: AnalyticsListener.EventTime?, volume: Float) {
        Timber.d("VolumeChanged == $volume")
    }

    //Player Event Listeners
    override fun onPlayerStateChanged(isLoading: Boolean, playbackState: Int) {
        Log.d("onPlayerError", "onPlayerStateChanged=$playbackState")
        Log.d("onPlayerError", "onPlayerStateChanged=$isLoading")
        when (playbackState) {
            Player.STATE_IDLE -> {
                radioViewState.value = RadioViewState.stop(radioDataModel)
            }
            Player.STATE_BUFFERING -> {
                radioViewState.value = RadioViewState.loading(radioDataModel)
            }
            Player.STATE_ENDED -> {
                //Player destroy
                radioViewState.value = RadioViewState.stop(radioDataModel)
            }
            Player.STATE_READY -> {
                // radio start in here
                if (simpleExoPlayer.playWhenReady) {
                    radioViewState.value = RadioViewState.playing(radioDataModel)
                } else {
                    radioViewState.value = RadioViewState.stop(radioDataModel)
                }
            }
        }
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        //is Error cause for mediaSource
        //Try to play again. Type.TYPE_SOURCE = 0
        //TODO implement
        {
            /*

                    Log.e("errror_deneme", "TYPE_SOURCE: " + error.getSourceException().getMessage());


                    if (error.getSourceException().getMessage().contains("Unable to connect")) {
                        if (service != null) {
                            if (sendedErrorId == -1) {
                                service.sendError(getChannel().getId() + "");
                                sendedErrorId = getChannel().getId();
                                return;
                            }
                        }
                    }
                    if (error.getSourceException().getMessage().contains("java.io.FileNotFoundException")) {
                        if (service != null) {
                            if (sendedErrorId == -1) {
                                service.sendError(getChannel().getId() + "");
                                sendedErrorId = getChannel().getId();
                                return;
                            }
                        }
                    }
                    if (error.getSourceException().getMessage().contains("Response code: 404")) {
                        if (service != null) {
                            if (sendedErrorId == -1) {
                                service.sendError(getChannel().getId() + "");
                                sendedErrorId = getChannel().getId();
                                return;
                            }
                        }
                    }
             */
        }

        if (error.type == 0) {
            startRadio(radioDataModel.radioStreamUrl, 0)
        } else {
            //TODO error fetch will be init.
            Timber.d("RadioError%s", error.message)
        }
    }


}