package com.jordiortuno.cleanmvvm.framework.ui.common

import android.content.Context
import android.widget.Toast


fun Context.showToast(message:String,length:Int){
    val toast = Toast.makeText(
        this,
        message,length
    )
    toast.show()
}
