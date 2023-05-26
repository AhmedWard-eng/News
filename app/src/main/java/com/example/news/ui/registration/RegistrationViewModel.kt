package com.example.news.ui.registration

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repo.AuthRepo
import com.example.news.domin.model.SignUpForm
import com.example.news.domin.model.SignUpResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel (private val repo: AuthRepo) : ViewModel(){

    private var signUpMutableData = MutableStateFlow<SignUpResult?>(null)
    var signUpData = signUpMutableData

    private var signUpMutableLoading = MutableStateFlow<Boolean?>(false)
    var signUpLoading = signUpMutableLoading

    private var signUpMutableSuccess = MutableStateFlow<Boolean?>(false)
    var signUpSuccess = signUpMutableSuccess

    private var signUpMutableError = MutableStateFlow<Boolean?>(false)
    var signUpError = signUpMutableError


    private var signUpMutableEmail = MutableStateFlow<String?>(null)
    var signUpEmail = signUpMutableEmail

    private var signUpMutableUserName = MutableStateFlow<String?>(null)
    var signUpUserName = signUpMutableUserName

    private var signUpMutablePassword = MutableStateFlow<String?>(null)
    var signUpPassword = signUpMutablePassword

    fun postSignUp(email: String, userName: String, password: String){
        viewModelScope.launch {
            signUpMutableLoading.value = true
            val form = SignUpForm(userName = userName , email = email , password = password)
            val response =  repo.signUP(form)
            signUpMutableLoading.value = false
            if(response.isSuccess){
                signUpMutableSuccess.value = true
                Log.i("Success", "Success")
            }else{
                signUpMutableError.value = true
                Log.i("Error", "Error")
            }
        }
    }

    fun validateEmail(email: String): Boolean {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUpEmail.value = "Invalid email"
            return false
        }
        return true
    }

    fun validateUserName(userName : String): Boolean {
        if (userName.isEmpty() || userName.length <= 2) {
            signUpUserName.value = "Invalid UserName"
            return false
        }
        return true
    }

    fun validatePassword(password : String): Boolean {
        if (password.isEmpty() && password.length >= 6) {
            signUpPassword.value = "Invalid Password"
            return false
        }
        return true
    }

}