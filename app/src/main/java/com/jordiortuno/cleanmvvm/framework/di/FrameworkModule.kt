package com.jordiortuno.cleanmvvm.framework.di

import android.content.Context
import com.jordiortuno.cleanmvvm.data.datasources.DocumentDataSource
import com.jordiortuno.cleanmvvm.framework.data.db.MajesticReaderDatabase
import com.jordiortuno.cleanmvvm.framework.data.localdatasource.MemoryDocumentDataSource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {

    @Provides
    fun documentDataSourceProvider(majesticReaderDatabase: MajesticReaderDatabase,@ApplicationContext context: Context):DocumentDataSource =
        MemoryDocumentDataSource(majesticReaderDatabase,context)
}