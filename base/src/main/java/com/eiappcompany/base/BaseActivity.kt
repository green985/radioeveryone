package com.eiappcompany.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.eiappcompany.base.util.viewState.Status
import com.eiappcompany.base.util.viewState.ViewState
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject
import javax.inject.Singleton


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

    private val loadingDialog: CustomDialog by lazy {
        CustomDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = viewModelFactory.create(getTClass())
        loadingDialog.show()
    }

    private fun getTClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
    }

    fun <T> observeViewState(viewState: ViewState<T>) {
        if (viewState.status == Status.LOADING) {
            setLoadingVisibility(true)
        } else {
            setLoadingVisibility(false)
        }

        if (viewState.status == Status.ERROR) {
            //TODO Error dialog show
        }
    }

    fun setLoadingVisibility(setShow: Boolean) {
        if (setShow) {
            if (!loadingDialog.isShowing) {
                loadingDialog.show()
            }
        } else {
            if (loadingDialog.isShowing) {
                loadingDialog.dismiss()
            }
        }
    }


}