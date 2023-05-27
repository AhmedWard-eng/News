package com.example.news.ui.logout

import androidx.lifecycle.ViewModel
import com.example.news.data.local.preferences.PreferencesData
import com.example.news.data.local.preferences.UserManager

class LogoutViewModel(private val preferencesData: UserManager = PreferencesData()) : ViewModel() {

    var userName: String = ""
        private set

    var email: String = ""
        private set

    init {
        getData()
    }

    private fun getData(){
        val result = preferencesData.getUserData()
        if (result.isSuccess) {
            result.onSuccess {
                userName = it.userName
                email = it.email
            }
        }
    }

    fun logout(){
        preferencesData.removeUserData()
    }
}