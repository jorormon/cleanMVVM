package com.jordiortuno.cleanmvvm.framework.ui.common

import android.os.Build
import android.text.Html
import android.text.Spanned

object HtmlUtilities {
    fun fromHtml(message: String): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(message)
        }
    }
}