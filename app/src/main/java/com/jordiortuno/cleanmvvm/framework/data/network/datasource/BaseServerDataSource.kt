package com.jordiortuno.cleanmvvm.framework.data.network.datasource

import com.jordiortuno.cleanmvvm.data.datasources.BaseRemoteDataSource
import com.jordiortuno.cleanmvvm.framework.data.network.api.BaseAPI

class BaseServerDataSource(private val api:BaseAPI):BaseRemoteDataSource {
    override suspend fun logOut(): Boolean {
        return api.logout().body()!!
    }
}