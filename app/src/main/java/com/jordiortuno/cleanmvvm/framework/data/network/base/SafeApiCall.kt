package com.jordiortuno.cleanmvvm.framework.data.network.base

import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> BaseResponse<T>
    ): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                val baseResponse = apiCall.invoke()
                when(baseResponse.code){
                    200,204 -> ResultWrapper.Success(baseResponse.data)
                    401->ResultWrapper.UnauthorizedError
                    else -> ResultWrapper.GenericError(null,null)
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        ResultWrapper.GenericError( throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        ResultWrapper.GenericError(null,null)
                    }
                }
            }

        }
    }
}