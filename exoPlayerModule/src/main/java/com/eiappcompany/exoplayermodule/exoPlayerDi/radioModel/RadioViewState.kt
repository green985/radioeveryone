package com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel


data class RadioViewState<out T>(
    val status: RadioStatus,
    val data: T,
    val message: String? = null
) {
    companion object {
        fun <T> playing(data: T): RadioViewState<T> {
            return RadioViewState(RadioStatus.PLAYING, data, null)
        }

        fun <T> stop(data: T): RadioViewState<T> {
            return RadioViewState(RadioStatus.PAUSE, data, null)
        }

        fun <T> error(data: T, msg: String): RadioViewState<T> {
            return RadioViewState(RadioStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T): RadioViewState<T> {
            return RadioViewState(RadioStatus.LOADING, data, null)
        }
    }
}
