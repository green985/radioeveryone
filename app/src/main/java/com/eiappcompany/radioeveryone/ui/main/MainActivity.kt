package com.eiappcompany.radioeveryone.ui.main

import android.os.Bundle
import com.eiappcompany.base.AppGlide
import com.eiappcompany.base.BaseActivity
import com.eiappcompany.base.util.helper.SharedHelper
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
        binding.denemeText.text = viewModel.k

        helper.putStringData("deneme", "dana")



        Timber.d("helper == " + helper.getStringData("deneme", "veriyok"))
    }
}
