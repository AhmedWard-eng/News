package com.example.news.ui.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.data.repo.news.NewsRepo
import com.example.news.ui.registration.RegistrationViewModel

class NewsDetailsViewModelFactory (private val repo: NewsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            NewsDetailsViewModel(repo) as T
        } else {
            throw IllegalAccessException("ViewModel class not found")
        }
    }
}