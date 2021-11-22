package com.jordiortuno.cleanmvvm.framework.ui.common

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setTextError(error: String?) {
    if (error != null) {
        this.isErrorEnabled = true
        this.error = error
    } else {
        isErrorEnabled = false
        this.error = null
    }
}