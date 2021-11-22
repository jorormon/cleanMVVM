package com.jordiortuno.cleanmvvm.data.repositories

import com.jordiortuno.cleanmvvm.data.datasources.DocumentDataSource
import com.jordiortuno.cleanmvvm.domain.Document

class DocumentRepository(
    private val documentDataSource: DocumentDataSource
) {

  suspend fun addDocument(document: Document) = documentDataSource.add(document)

  suspend fun getDocuments() = documentDataSource.readAll()
  
  suspend fun removeDocument(document: Document) = documentDataSource.remove(document)

}