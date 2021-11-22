package com.jordiortuno.cleanmvvm.domain

import okhttp3.ResponseBody

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val errorBody: ResponseBody?): ResultWrapper<Nothing>()
    data class FormError(val errors:Map<String,String>): ResultWrapper<Nothing>()
    object UnauthorizedError: ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
    object Loading: ResultWrapper<Nothing>()
}