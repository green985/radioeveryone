package com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel

import android.util.Log
import com.eiappcompany.base.util.viewState.Status
import com.eiappcompany.base.util.viewState.ViewState


data class RadioViewState<out T>(val status: Status, val data: T, val message: String? = null) {
    companion object {
        fun <T> playing(data: T): ViewState<T> {
            return ViewState(Status.SUCCESS, data, null)
        }

        fun <T> stop(data: T): ViewState<T> {
            return ViewState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): ViewState<T> {
            return ViewState(Status.ERROR, null, msg)
        }

        fun <T> loading(data: T? = null): ViewState<T> {
            return ViewState(Status.LOADING, data, null)
        }
    }
}
