package com.jordiortuno.cleanmvvm.framework.di

import android.app.Application
import android.content.Context
import com.jordiortuno.cleanmvvm.framework.data.db.MajesticReaderDatabase
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(app:Application):String = "1234567890a"


    @Provides
    @Singleton
    fun localDatabaseProvider(@ApplicationContext context: Context):MajesticReaderDatabase =
        MajesticReaderDatabase.getInstance(context)

}