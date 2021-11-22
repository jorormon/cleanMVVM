package com.jordiortuno.cleanmvvm.data.datasources

import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.LoginResponse

interface AuthRemoteDataSource {

    suspend fun login(email:String,password:String): ResultWrapper<LoginResponse>
    suspend fun register():Boolean
    suspend fun changePassword():Boolean
}