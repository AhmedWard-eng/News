package com.example.news.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.local.preferences.PreferencesData
import com.example.news.data.remote.datasource.AuthRemoteDataSource
import com.example.news.data.remote.datasource.AuthRemoteDataSourceImp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val authRemoteDataSource: AuthRemoteDataSource = AuthRemoteDataSourceImp()) : ViewModel() {
    private var _navigate = MutableStateFlow(Destination.LOADING)

    var navigate = _navigate.asStateFlow()

    init {
        whereToNavigate()
    }

    private fun whereToNavigate(){
        viewModelScope.launch {
            delay(1500)
            if (authRemoteDataSource.isUserAuth()){
                _navigate.emit(Destination.HOME)
            }else{
                _navigate.emit(Destination.LOGIN)
            }
        }
    }
}

enum class Destination{
    HOME,
    LOGIN,
    LOADING
}