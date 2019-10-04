package com.eiappcompany.base.util.ext

import android.os.Build
import android.text.Html
import android.text.Spanned

private const val HTTPS_PREFIX = "https://"

@Suppress("DEPRECATION")
fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun String.addHttpsPrefix(): String {
    return if (startsWith(HTTPS_PREFIX)) {
        this
    } else {
        replace("^http?://".toRegex(), HTTPS_PREFIX)
    }
}
