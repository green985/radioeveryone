package com.eiappcompany.base.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import com.eiappcompany.base.R
import kotlinx.android.synthetic.main.dialog_error_layout.*

class ErrorDialogView constructor(
    dialogContext: Context
) : Dialog(dialogContext, R.style.Theme_Radio_Dialog) {


    var dialogTitleStr: String? = null
    var dialogDesc: String = ""
    var action: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_error_layout)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
    }

    private fun initSetting() {
        setAgainButtonClick()
        setText()
        setCause()
        setCloseButton()
    }

    private fun setCloseButton() {
        closeButton.setOnClickListener {
            this.dismiss()
        }
    }

    private fun setCause() {
        errorCause.text = dialogDesc
    }

    private fun setText() {
        dialogTitleStr?.let {
            dialogTitle.text = dialogTitleStr
            dialogTitle.visibility = View.VISIBLE
            return
        }

        dialogTitle.visibility = View.GONE
    }

    private fun setAgainButtonClick() {
        againErrorButton.setOnClickListener {
            action?.let {
                it()
                dismiss()
            }

        }

    }


    fun showDialog() {
        this.show()
        initSetting()
    }

}