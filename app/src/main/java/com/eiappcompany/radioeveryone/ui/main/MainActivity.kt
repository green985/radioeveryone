package com.eiappcompany.radioeveryone.ui.main

import androidx.lifecycle.Observer
import com.eiappcompany.base.BaseActivity
import com.eiappcompany.base.errorModel.ErrorActionModel
import com.eiappcompany.base.util.helper.SharedHelper
import com.eiappcompany.base.util.viewState.Status
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioDataModel
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioStatus
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioViewState
import com.eiappcompany.radioeveryone.R
import com.eiappcompany.radioeveryone.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {


    @Inject
    lateinit var helper: SharedHelper

    override val layoutId = R.layout.activity_main


    override fun prepareView() {
        binding.denemeText.setOnClickListener {
            viewModel.startRadio()
        }

        binding.stopTextView.setOnClickListener {
            viewModel.stopRadio()
        }

        binding.destroyRadio.setOnClickListener {
            viewModel.destroyRadio()
        }
        binding.differentRadio.setOnClickListener {
            viewModel.startdifferentRadioRadio()
        }
    }

    override fun prepareObserver() {
        viewModel.loginResult.observe(this, Observer {
            observeViewState(it) {
                viewModel.doLogin()
            }

        })

        viewModel.radioExo.radioViewState.observe(this, Observer {
            observeRadioState(it)
        })
    }

    override fun prepareSomethingLateImplement() {

    }


    fun observeRadioState(
        viewState: RadioViewState<RadioDataModel>,
        errorAction: (() -> Unit)? = null
    ) {
        if (viewState.status == RadioStatus.LOADING) {
            //setLoadingVisibility(true)
            binding.playingName.text = viewState.data.radioName.plus(" ".plus(viewState.status))
            return
        } else {
            setLoadingVisibility(false)
        }

        if (viewState.status == RadioStatus.PLAYING) {
            //Playing status
            binding.playingName.text = viewState.data.radioName.plus(" ".plus(viewState.status))
        }
        if (viewState.status == RadioStatus.PAUSE) {
            //Pause status
            binding.playingName.text = viewState.data.radioName.plus(" ".plus(viewState.status))
        }

        if (viewState.status == Status.ERROR) {
            //TODO Error dialog show
            exceptionHandler(ErrorActionModel(Throwable(viewState.message), errorAction))
        }
    }


}
