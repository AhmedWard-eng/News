package com.example.news.ui.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repo.AuthRepo
import com.example.news.data.repo.AuthRepoImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepo: AuthRepo = AuthRepoImp()) : ViewModel() {
    private val _loginResponse = MutableStateFlow<Boolean>(false)
    val loginResponse: StateFlow<Boolean> = _loginResponse
    private val _loading = MutableStateFlow<Boolean>(false)
    val loading = _loading
    var error: String = ""
        private set

    fun login(email: String, password: String) {

        viewModelScope.launch {
            loading.value = true
            var result = authRepo.login(email, password)
            loading.value = false
            if (authRepo.login(email, password))
                _loginResponse.value = true
            else {
                _loginResponse.value = false
                error = "UserName or Password not valid"
            }

        }
    }
}