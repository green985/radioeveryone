package com.eiappcompany.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


/**
Created by EiAppCompany
04-10-2019
11:23
 **/

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : DaggerAppCompatActivity() {

    lateinit var binding: DB
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = viewModelFactory.create(getTClass())
    }

    private fun getTClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
    }

}