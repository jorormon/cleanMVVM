package com.jordiortuno.cleanmvvm.usecases.auth

import com.jordiortuno.cleanmvvm.data.repositories.MainRepository
import com.jordiortuno.cleanmvvm.framework.data.network.RemoteClient
import com.jordiortuno.cleanmvvm.framework.data.network.api.UserAPI
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.UserResponse
import com.jordiortuno.cleanmvvm.framework.data.network.datasource.MainServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUseCase {
    suspend operator fun invoke(): ResultWrapper<UserResponse> = withContext(Dispatchers.IO){
        val mainRepository = MainRepository(MainServerDataSource(RemoteClient.buildApi(UserAPI::class.java)))

        mainRepository.getUser()

    }
}