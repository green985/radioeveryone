package com.eiappcompany.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import timber.log.Timber

/**
 * Custom Loading Dialog
 */
class CustomDialog(context: Context) : Dialog(context) {

    init {
        Timber.d("CustomDialog call")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_progress_layout)
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)

        //lottieAnim.setAnimation("/radio_loading_lottie.json")

    }

}