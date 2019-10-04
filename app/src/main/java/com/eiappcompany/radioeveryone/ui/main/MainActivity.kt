package com.eiappcompany.radioeveryone.ui.main

import android.content.Context
import android.os.Bundle
import com.eiappcompany.base.BaseActivity
import com.eiappcompany.radioeveryone.R
import com.eiappcompany.radioeveryone.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {

    @Inject
    lateinit var context: Context

    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.denemeText.text = viewModel.k
    }
}
