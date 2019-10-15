package com.eiappcompany.radioeveryone.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.eiappcompany.base.util.viewState.Status
import com.eiappcompany.exoplayermodule.exoPlayerDi.radio.RadioClass
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioDataModel
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioStatus
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioViewState
import com.eiappcompany.radioeveryone.R
import kotlinx.android.synthetic.main.radio_player_view.view.*
import javax.inject.Inject

/**
Created by EiAppCompany
14-10-2019
14:14
 **/
class RadioPlayerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {


    var radioPlayer: RadioClass? = null
        set(value) {
            field = value
            observeRadio()
        }


    var lifecycleOwner: LifecycleOwner? = null
//    var radioDataModel = MutableLiveData<RadioViewState<RadioDataModelCrowler>>()


    init {
        LayoutInflater.from(context)
            .inflate(R.layout.radio_player_view, this, true)

    }


    fun observeRadio() {
        lifecycleOwner?.let { www ->
            radioPlayer?.radioViewState?.observe(www, Observer {
                observeRadioState(it)
            })
        }
    }


    fun observeRadioState(
        viewState: RadioViewState<RadioDataModel>,
        errorAction: (() -> Unit)? = null
    ) {

        radioName.text = viewState.data.radioName


        if (viewState.status == RadioStatus.LOADING) {
            //setLoadingVisibility(true)
            radioStatus.text = viewState.data.radioName.plus(" ".plus(viewState.status))
            return
        }
        if (viewState.status == RadioStatus.PLAYING) {
            //Playing status
            radioStatus.text = viewState.data.radioName.plus(" ".plus(viewState.status))
        }
        if (viewState.status == RadioStatus.PAUSE) {
            //Pause status
            radioStatus.text = viewState.data.radioName.plus(" ".plus(viewState.status))
        }

        if (viewState.status == Status.ERROR) {
            //TODO Error dialog show
            radioStatus.text = "Hata"
        }
    }


}