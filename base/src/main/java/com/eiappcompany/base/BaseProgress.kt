package com.eiappcompany.base

import android.content.Context
import android.graphics.PorterDuff
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

/**
 * Can use for glide image loading anim or view loading anim
 */
class BaseProgress(@NonNull context: Context) : CircularProgressDrawable(context) {

    init {
        init(context)
    }

    private fun init(context: Context) {
        setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
        this.strokeWidth = 5.0F
        this.centerRadius = 30.0F
        this.start()
    }

}