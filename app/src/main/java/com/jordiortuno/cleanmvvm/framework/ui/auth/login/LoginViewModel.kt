package com.jordiortuno.cleanmvvm.framework.ui.auth.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.domain.responses.LoginResponse
import com.jordiortuno.cleanmvvm.usecases.auth.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

   // private val login = LoginUseCase(AuthRepository(ServerAuthRemoteDataSource(RemoteClient.service)) )

    private val _resultLogin = MutableLiveData<ResultWrapper<LoginResponse>>()
    val resultLogin: LiveData<ResultWrapper<LoginResponse>> get() = _resultLogin

    fun onSubmitLogin(email: String, password: String) {
        val emailCheck = checkEmailCorrect(email)
        val passwordCheck = checkPasswordValid(password)


            viewModelScope.launch(Dispatchers.Main) {
                _resultLogin.value = ResultWrapper.Loading
                _resultLogin.value =LoginUseCase().invoke()
            }


    }

    fun checkEmailCorrect(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun checkPasswordValid(password: String): Boolean {
        //Add regular expresion
        return password.length > 8 //Cambiar por la expresión regular deseada
    }
}