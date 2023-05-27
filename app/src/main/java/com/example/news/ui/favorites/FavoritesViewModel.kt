package com.example.news.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repo.news.NewsRepo
import com.example.news.data.repo.news.NewsRepoImp
import com.example.news.domin.model.News
import kotlinx.coroutines.launch

class FavoritesViewModel(private val newsRepo: NewsRepo = NewsRepoImp()) : ViewModel() {

    val news = newsRepo.getFavorites()
    fun deleteFromFav(news:News){
        viewModelScope.launch {
            newsRepo.removeFromFavorites(news)
        }
    }
}