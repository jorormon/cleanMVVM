package com.jordiortuno.cleanmvvm.framework.data.network.datasource

import com.jordiortuno.cleanmvvm.data.datasources.MainRemoteDataSource
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.UserResponse
import com.jordiortuno.cleanmvvm.framework.data.network.api.UserAPI
import com.jordiortuno.cleanmvvm.framework.data.network.base.SafeApiCall

class MainServerDataSource(private val userAPI:UserAPI):MainRemoteDataSource,SafeApiCall {
    override suspend fun getUser(): ResultWrapper<UserResponse> {
        return safeApiCall {
            userAPI.getUser()
        }
    }

    override suspend fun logOut(): Boolean {
        TODO("Not yet implemented")
    }
}