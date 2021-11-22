package com.jordiortuno.cleanmvvm.framework.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.UserResponse
import com.jordiortuno.cleanmvvm.usecases.auth.GetUserUseCase
import com.jordiortuno.cleanmvvm.usecases.main.GetDocuments
import kotlinx.coroutines.launch


class HomeViewModel(private val getDocuments: GetDocuments): ViewModel() {
    private val _getUser = MutableLiveData<ResultWrapper<UserResponse>>()
    val getUser: LiveData<ResultWrapper<UserResponse>> get() = _getUser
    fun getUser(){
        viewModelScope.launch {
            _getUser.value = ResultWrapper.Loading
            _getUser.value = GetUserUseCase().invoke()

        }
    }
    fun loadDocuments(){
        viewModelScope.launch{
            getDocuments()
        }
    }

}