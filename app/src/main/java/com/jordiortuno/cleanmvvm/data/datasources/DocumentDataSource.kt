package com.jordiortuno.cleanmvvm.data.datasources

import com.jordiortuno.cleanmvvm.domain.Document


interface DocumentDataSource {

  suspend fun add(document: Document)

  suspend fun readAll(): List<Document>

  suspend fun remove(document: Document)
}
