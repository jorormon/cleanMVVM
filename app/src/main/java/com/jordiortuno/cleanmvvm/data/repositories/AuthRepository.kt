package com.jordiortuno.cleanmvvm.data.repositories

import com.jordiortuno.cleanmvvm.data.datasources.AuthRemoteDataSource
import com.jordiortuno.cleanmvvm.framework.data.network.Provider
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.LoginResponse

class AuthRepository(private val authRemoteDataSource: AuthRemoteDataSource) {

    suspend fun login(email:String,password:String) : ResultWrapper<LoginResponse> {
        val response = authRemoteDataSource.login(email,password)
        if(response is ResultWrapper.Success){
                saveAccessTokens(response.value.accessToken,response.value.refreshToken)
        }
        return response
    }

    suspend fun register():Boolean = authRemoteDataSource.register()



    private suspend fun saveAccessTokens(accessToken:String, refreshToken:String){
        Provider.saveAccessTokens(accessToken, refreshToken)
    }
}