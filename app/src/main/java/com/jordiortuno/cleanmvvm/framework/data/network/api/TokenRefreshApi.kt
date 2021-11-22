package com.jordiortuno.cleanmvvm.framework.data.network.api

import com.jordiortuno.cleanmvvm.domain.responses.BaseResponse
import com.jordiortuno.cleanmvvm.domain.responses.LoginResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenRefreshApi : BaseAPI {

    @POST("refresh")
    suspend fun refreshAccessToken(
        @Body requestBody:RequestBody
    ): BaseResponse<LoginResponse>
}