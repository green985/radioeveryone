package com.eiappcompany.radioeveryone.ui.main

import androidx.lifecycle.Observer
import com.eiappcompany.base.BaseActivity
import com.eiappcompany.base.util.helper.SharedHelper
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
    }

    override fun prepareObserver() {
        viewModel.loginResult.observe(this, Observer {
            observeViewState(it) {
                viewModel.doLogin()
            }

        })

        viewModel.radioExo.radioViewState.observe(this, Observer {
            observeViewState(it)
        })
    }

    override fun prepareSomethingLateImplement() {

    }


}
