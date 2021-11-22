package com.jordiortuno.cleanmvvm.framework.data.network.common

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

fun JSONObject.toRequestBody(): RequestBody {
    return this.toString().toRequestBody("application/json".toMediaTypeOrNull())
}