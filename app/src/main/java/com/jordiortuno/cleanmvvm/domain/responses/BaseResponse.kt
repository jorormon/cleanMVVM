package com.jordiortuno.cleanmvvm.domain.responses

data class BaseResponse<out T>(

    val data:T,
    val message:String,
    val code:Int,
    val error:String

)