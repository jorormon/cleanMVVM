package com.jordiortuno.cleanmvvm.framework.di

import com.jordiortuno.cleanmvvm.data.datasources.DocumentDataSource
import com.jordiortuno.cleanmvvm.data.repositories.DocumentRepository
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun documentRepositoryProvider(documentDataSource: DocumentDataSource) =
        DocumentRepository(documentDataSource)

}