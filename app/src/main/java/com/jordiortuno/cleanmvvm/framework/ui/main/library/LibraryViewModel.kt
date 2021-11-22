package com.jordiortuno.cleanmvvm.framework.ui.main.library

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordiortuno.cleanmvvm.domain.Document
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.UserResponse
import com.jordiortuno.cleanmvvm.usecases.auth.GetUserUseCase
import com.jordiortuno.cleanmvvm.usecases.main.AddDocument
import com.jordiortuno.cleanmvvm.usecases.main.GetDocuments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(private val getDocuments: GetDocuments, private val addDocument: AddDocument): ViewModel() {
    val documents: MutableLiveData<List<Document>> = MutableLiveData()

    fun loadDocuments(){
        viewModelScope.launch{
            documents.postValue(getDocuments())
        }
    }
    fun addDocument(uri: Uri){
        viewModelScope.launch {
            addDocument(Document(uri.toString(),"",0,""))
        }
    }

}