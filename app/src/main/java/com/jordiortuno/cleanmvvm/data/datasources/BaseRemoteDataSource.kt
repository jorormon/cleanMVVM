package com.jordiortuno.cleanmvvm.data.datasources

interface BaseRemoteDataSource {
    suspend fun logOut():Boolean
}