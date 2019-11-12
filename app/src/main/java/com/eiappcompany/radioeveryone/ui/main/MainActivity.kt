package com.eiappcompany.radioeveryone.ui.main

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eiappcompany.base.BaseActivity
import com.eiappcompany.base.crowler.crowlerModel.RadioDataModelCrowler
import com.eiappcompany.base.errorModel.ErrorActionModel
import com.eiappcompany.base.util.helper.SharedHelper
import com.eiappcompany.base.util.interfaces.RecyclerViewItemClickListener
import com.eiappcompany.base.util.viewState.Status
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioDataModel
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioStatus
import com.eiappcompany.exoplayermodule.exoPlayerDi.radioModel.RadioViewState
import com.eiappcompany.radioeveryone.R
import com.eiappcompany.radioeveryone.databinding.ActivityMainBinding
import com.eiappcompany.radioeveryone.ui.radioListAdapter.RadioListAdapter
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(),
    RecyclerViewItemClickListener<RadioDataModelCrowler> {


    @Inject
    lateinit var helper: SharedHelper

    lateinit var radioListAdapter: RadioListAdapter

    override val layoutId = R.layout.activity_main


    override fun prepareView() {
        binding.radioView.lifecycleOwner = this
        binding.radioView.radioPlayer = viewModel.radioExo

        binding.stopTextView.setOnClickListener {
            viewModel.stopRadio()
        }

        binding.destroyRadio.setOnClickListener {
            viewModel.destroyRadio()
        }
        binding.differentRadio.setOnClickListener {
            viewModel.startdifferentRadioRadio()
            setTextToEksiView()
        }

        setTextToEksiView()
    }

     fun setTextToEksiView(){
        binding.eksiTextViewExample.setHardcodedString(getString(R.string.denemeText))
    }

    override fun prepareObserver() {
        /*
        viewModel.loginResult.observe(this, Observer {
            observeViewState(it) {
                viewModel.doLogin()
            }
        })
         */

        viewModel.radioExo.radioViewState.observe(this, Observer {
            observeRadioState(it)
        })

        viewModel.radioListLiveData.observe(this, Observer {
            prepareRecyclerViewForRadioList(it)
        })


    }

    override fun prepareSomethingLateImplement() {
    }

    fun prepareRecyclerViewForRadioList(it: ViewState<List<RadioDataModelCrowler>>) {
        if (it.data == null) {
            //TODO Null check radioData
            return
        }

        radioListAdapter = RadioListAdapter(it.data!!, this)
        binding.root.removeView(binding.radioView)
        radioListAdapter.addView = WeakReference(binding.radioView)


        binding.recyclerViewRadioList.apply {
            adapter = radioListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun listItemClick(listItem: RadioDataModelCrowler, it: View, adapterPosition: Int) {
        viewModel.startRadio(listItem)
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
