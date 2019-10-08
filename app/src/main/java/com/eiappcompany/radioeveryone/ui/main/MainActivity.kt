package com.eiappcompany.radioeveryone.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.eiappcompany.base.BaseActivity
import com.eiappcompany.base.util.helper.SharedHelper
import com.eiappcompany.base.util.viewState.Status
import com.eiappcompany.base.util.viewState.ViewState
import com.eiappcompany.radioeveryone.R
import com.eiappcompany.radioeveryone.databinding.ActivityMainBinding
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {

    @Inject
    lateinit var helper: SharedHelper

    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        helper.putStringData("deneme", "dana")

        viewModel.loginResult.observe(this, Observer {
            observerStatus(it) {
                Log.d("ViewState", "data observe ettim ")
            }
        })

        Timber.d("helper == " + helper.getStringData("deneme", "veriyok"))
    }

    fun <T> observerStatus(viewState: ViewState<T>, action: (T) -> Unit) {
        if (viewState.status == Status.LOADING) {

            Log.d("ViewState", "showLoading")
        } else if (viewState.status == Status.SUCCESS) {
            Log.d("ViewState", "hide")
            action(viewState.data!!)
        } else if (viewState.status == Status.ERROR) {
            Log.d("ViewState", "hide loading")
            Log.d("ViewState", "error dinledim " + viewState.message)

        }
    }
}
