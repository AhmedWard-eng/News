package com.example.news.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.data.repo.auth.AuthRepo

class RegistViewModelFactory (private val repo: AuthRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            RegistrationViewModel(repo) as T
        } else {
            throw IllegalAccessException("ViewModel class not found")
        }
    }
}