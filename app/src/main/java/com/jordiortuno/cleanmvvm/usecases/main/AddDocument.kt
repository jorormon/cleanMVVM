package com.jordiortuno.cleanmvvm.usecases.main

import com.jordiortuno.cleanmvvm.data.repositories.DocumentRepository
import com.jordiortuno.cleanmvvm.domain.Document

class AddDocument(private val documentRepository: DocumentRepository) {
    suspend operator fun invoke(document: Document){
        documentRepository.addDocument(document)
    }
}