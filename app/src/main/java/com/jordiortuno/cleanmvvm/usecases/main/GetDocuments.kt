package com.jordiortuno.cleanmvvm.usecases.main

import com.jordiortuno.cleanmvvm.data.repositories.DocumentRepository

class GetDocuments(private val documentRepository: DocumentRepository) {
    suspend operator fun invoke() =
        documentRepository.getDocuments()

}