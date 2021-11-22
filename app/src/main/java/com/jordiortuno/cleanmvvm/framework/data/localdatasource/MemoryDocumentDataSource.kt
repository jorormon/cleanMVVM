package com.jordiortuno.cleanmvvm.framework.data.localdatasource

import android.content.Context
import com.jordiortuno.cleanmvvm.data.datasources.DocumentDataSource
import com.jordiortuno.cleanmvvm.domain.Document
import com.jordiortuno.cleanmvvm.framework.data.db.DocumentEntity
import com.jordiortuno.cleanmvvm.framework.data.db.MajesticReaderDatabase
import com.jordiortuno.cleanmvvm.framework.utils.FileUtil
import dagger.hilt.android.qualifiers.ApplicationContext

class MemoryDocumentDataSource(private val majesticReaderDatabase: MajesticReaderDatabase,
                               @ApplicationContext val context:Context):DocumentDataSource {
    private val documentDao = majesticReaderDatabase.documentDao()
    override suspend fun add(document: Document) {
        val details = FileUtil.getDocumentDetails(context,document.uri)
         documentDao.addDocument(DocumentEntity(document.uri,details.name,details.size,details.thumbnail))
    }

    override suspend fun readAll(): List<Document> =
        documentDao.getDocuments().map{Document(it.uri,it.title,it.size,it.thumbnailUri)}


    override suspend fun remove(document: Document) {
        documentDao.removeDocument(DocumentEntity(document.uri,document.name,document.size,document.thumbnail))
    }
}