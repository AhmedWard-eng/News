package com.example.news.ui.logout

import androidx.lifecycle.ViewModel
import com.example.news.data.local.preferences.PreferencesData
import com.example.news.data.local.preferences.UserManager
import kotlinx.coroutines.flow.MutableStateFlow

class LogoutViewModel(private val preferencesData: UserManager = PreferencesData()) : ViewModel() {

    private var userNameMutableError = MutableStateFlow<String?>(null)
    var userName = userNameMutableError

    private var emailMutableError = MutableStateFlow<String?>(null)
    var email = emailMutableError

    init {
        getData()
    }

    private fun getData(){
        val result = preferencesData.getUserData()
        if (result.isSuccess) {
            result.onSuccess {
                userNameMutableError.value = it.userName
                emailMutableError.value = it.email
            }
        }
    }

    fun logout(){
        preferencesData.removeUserData()
    }
}