package com.jordiortuno.cleanmvvm.framework.di

import com.jordiortuno.cleanmvvm.data.repositories.DocumentRepository
import com.jordiortuno.cleanmvvm.usecases.main.AddDocument
import com.jordiortuno.cleanmvvm.usecases.main.GetDocuments
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun getDocumentsProvider(documentRepository: DocumentRepository) =
        GetDocuments(documentRepository)

    @Provides
    fun addDocumentProvider(documentRepository: DocumentRepository) =
        AddDocument(documentRepository)

}