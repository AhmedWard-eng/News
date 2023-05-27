package com.example.news.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repo.news.NewsRepo
import com.example.news.data.repo.news.NewsRepoImp
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepo: NewsRepo = NewsRepoImp()) : ViewModel() {
    init {
        viewModelScope.launch { newsRepo.refreshNews()
        }
    }
    val news = newsRepo.getNews()
}