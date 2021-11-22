package com.jordiortuno.cleanmvvm.framework.data.network.api

import com.jordiortuno.cleanmvvm.framework.data.network.base.Retry
import com.jordiortuno.cleanmvvm.domain.responses.BaseResponse
import com.jordiortuno.cleanmvvm.domain.responses.UserResponse
import retrofit2.http.GET

interface UserAPI: BaseAPI {

    @Retry
    @GET("profile")
    suspend fun getUser(): BaseResponse<UserResponse>
}