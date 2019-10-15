package com.eiappcompany.radioeveryone.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.eiappcompany.base.AppGlide
import com.eiappcompany.base.BaseProgress
import com.eiappcompany.base.util.ext.addHttpsPrefix
import com.eiappcompany.radioeveryone.R
import javax.inject.Inject

class ImageBindingAdapter @Inject constructor() {
    @BindingAdapter(value = ["android:src"])
    fun ImageView.bindImage(url: String?) {
        if (!url.isNullOrEmpty()) {
            AppGlide.with(context)
                    .asBitmap()
                    .load(url.addHttpsPrefix())
                    .placeholder(BaseProgress(context))
                    .error(R.mipmap.ic_launcher)
                    .into(this)
        }
    }
}
