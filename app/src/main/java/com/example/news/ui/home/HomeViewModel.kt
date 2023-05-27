package com.example.news.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repo.news.NewsRepo
import com.example.news.data.repo.news.NewsRepoImp
import com.example.news.domin.model.News
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepo: NewsRepo = NewsRepoImp()) : ViewModel() {
    var list: List<News>? = null
    init {
        viewModelScope.launch { newsRepo.refreshNews()
        }
    }
    val news = newsRepo.getNews()
}