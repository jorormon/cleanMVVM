package com.jordiortuno.cleanmvvm.usecases.auth

import com.jordiortuno.cleanmvvm.data.repositories.AuthRepository
import com.jordiortuno.cleanmvvm.framework.data.network.RemoteClient
import com.jordiortuno.cleanmvvm.framework.data.network.api.AuthAPI
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.framework.data.network.datasource.AuthServerDataSource
import com.jordiortuno.cleanmvvm.domain.responses.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class LoginUseCase() {
    suspend fun invoke(): ResultWrapper<LoginResponse> = withContext(Dispatchers.IO){
        val authRepository = AuthRepository(AuthServerDataSource(RemoteClient.buildAuthApi(AuthAPI::class.java)))
        authRepository.login("jordi.om@gmail.com","1234567")

    }
}