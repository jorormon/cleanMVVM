package com.jordiortuno.cleanmvvm.framework.data.network.api

import com.jordiortuno.cleanmvvm.framework.data.network.base.Retry
import retrofit2.Response
import retrofit2.http.GET

interface BaseAPI {
    //TODO THIS CLASS WILL CONTAIN RETROFIT CALLS

    @Retry
    @GET("logout")
    suspend fun logout():Response<Boolean>

}