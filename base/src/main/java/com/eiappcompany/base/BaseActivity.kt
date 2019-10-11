package com.eiappcompany.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.eiappcompany.base.dialogs.CustomDialog
import com.eiappcompany.base.dialogs.ErrorDialogView
import com.eiappcompany.base.errorModel.ErrorActionModel
import com.eiappcompany.base.util.viewState.Status
import com.eiappcompany.base.util.viewState.ViewState
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

    private val loadingDialog: CustomDialog by lazy {
        CustomDialog(this)
    }
    private lateinit var errorDialog: ErrorDialogView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = viewModelFactory.create(getTClass())
        prepareObserver()
        prepareView()
        prepareSomethingLateImplement()

    }

    abstract fun prepareView()
    abstract fun prepareObserver()
    abstract fun prepareSomethingLateImplement()

    private fun getTClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
    }

    fun <T> observeViewState(viewState: ViewState<T>, errorAction: (() -> Unit)? = null) {
        if (viewState.status == Status.LOADING) {
            setLoadingVisibility(true)
        } else {
            setLoadingVisibility(false)
        }

        if (viewState.status == Status.ERROR) {
            //TODO Error dialog show
            exceptionHandler(ErrorActionModel(Throwable(viewState.message), errorAction))
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

    fun exceptionHandler(errorModel: ErrorActionModel) {
        errorDialog = ErrorDialogView(this)
        errorDialog.dialogTitleStr = "Bir hata oluştu."
        errorDialog.dialogDesc = errorModel.throwable.message.toString()
        errorModel.action.let {
            errorDialog.action = it
            errorDialog.dismiss()
        }
        errorDialog.showDialog()
    }


}