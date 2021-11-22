package com.jordiortuno.cleanmvvm.data.datasources

import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.UserResponse

interface MainRemoteDataSource:BaseRemoteDataSource {
    suspend fun getUser():ResultWrapper<UserResponse>

}