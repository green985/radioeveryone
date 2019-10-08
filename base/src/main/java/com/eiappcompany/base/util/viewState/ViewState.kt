package com.eiappcompany.base.util.viewState

import android.util.Log

data class ViewState<out T>(val status: Status, val data: T?, val message: String? = null) {
    companion object {
        fun <T> success(data: T? = null): ViewState<T> {
            Log.d("ViewState", "success")
            return ViewState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): ViewState<T> {
            Log.d("ViewState", "error viewState")
            return ViewState(Status.ERROR, null, msg)
        }

        fun <T> loading(data: T? = null): ViewState<T> {
            Log.d("ViewState", "loading")
            return ViewState(Status.LOADING, data, null)
        }
    }
}
