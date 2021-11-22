package com.jordiortuno.cleanmvvm.data.repositories

import com.jordiortuno.cleanmvvm.data.datasources.MainRemoteDataSource
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.UserResponse

class MainRepository(private val mainRemoteDataSource: MainRemoteDataSource) {

    suspend fun getUser():ResultWrapper<UserResponse>{
        return mainRemoteDataSource.getUser()
    }
}