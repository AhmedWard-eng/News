package com.example.news.ui.login


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.news.data.repo.auth.AuthRepo
import com.example.news.data.repo.auth.AuthRepoImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepo: AuthRepo = AuthRepoImp()) : ViewModel() {
    private val _loginResponse = MutableStateFlow<Boolean?>(null)
    val loginResponse: StateFlow<Boolean?> = _loginResponse
    private val _loading = MutableStateFlow<Boolean>(false)
    val loading = _loading
    var error: String = ""
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loading.value = true
            val result = authRepo.login(email, password)
            loading.value = false
            if (result.isSuccess){
                _loginResponse.value = true
            }else {
                result.onFailure {
                    error = it.message ?: ""
                }
                _loginResponse.value = false
            }
            _loginResponse.value = null

        }
    }
}