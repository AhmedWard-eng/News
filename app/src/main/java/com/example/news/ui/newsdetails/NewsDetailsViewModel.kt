package com.example.news.ui.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repo.news.NewsRepo
import com.example.news.domin.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsDetailsViewModel(private val repo: NewsRepo) : ViewModel() {

    private var newsMutableData = MutableStateFlow<News?>(null)
    var newsData = newsMutableData

    fun deleteFromFav(currentNew : News){
        viewModelScope.launch {
            repo.removeFromFavorites(currentNew)
        }
    }

    fun addToFav(currentNew : News){
        viewModelScope.launch {
            repo.addToFavorites(currentNew)
        }
    }
}