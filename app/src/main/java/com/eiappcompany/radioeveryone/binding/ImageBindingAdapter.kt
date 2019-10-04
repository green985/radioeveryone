package com.eiappcompany.radioeveryone.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.eiappcompany.base.AppGlide
import com.eiappcompany.base.util.ext.addHttpsPrefix
import javax.inject.Inject

class ImageBindingAdapter @Inject constructor() {
    @BindingAdapter(value = ["android:src"])
    fun ImageView.bindImage(url: String?) {
        /*
        if (!url.isNullOrEmpty()) {
            AppGlide.with(context)
                .asBitmap()
                .load(url.addHttpsPrefix())
                .into(this)

        }


         */
    }
}
