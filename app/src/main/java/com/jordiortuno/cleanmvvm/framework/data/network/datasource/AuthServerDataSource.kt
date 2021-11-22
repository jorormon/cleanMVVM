package com.jordiortuno.cleanmvvm.framework.data.network.datasource

import com.jordiortuno.cleanmvvm.data.datasources.AuthRemoteDataSource
import com.jordiortuno.cleanmvvm.framework.data.network.api.AuthAPI
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.framework.data.network.base.SafeApiCall
import com.jordiortuno.cleanmvvm.domain.responses.LoginResponse
import com.jordiortuno.cleanmvvm.framework.data.network.common.toRequestBody
import org.json.JSONObject

class AuthServerDataSource(private val authAPI: AuthAPI):AuthRemoteDataSource,SafeApiCall {

    override suspend fun login(email: String, password: String): ResultWrapper<LoginResponse> {
        return safeApiCall {
            val jsonObject = JSONObject()
            jsonObject.put("email",email)
            jsonObject.put("password",password)
            val body = jsonObject.toRequestBody()
            authAPI.login(body)
        }
    }

    override suspend fun register(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun changePassword(): Boolean {
        TODO("Not yet implemented")
    }

}