package com.jordiortuno.cleanmvvm.framework.data.network.api

import com.jordiortuno.cleanmvvm.framework.data.network.base.Retry
import com.jordiortuno.cleanmvvm.domain.responses.LoginResponse
import com.jordiortuno.cleanmvvm.domain.responses.BaseResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI : BaseAPI {

    @Retry
    @POST("login")
    suspend fun login(@Body requestBody: RequestBody): BaseResponse<LoginResponse>
}