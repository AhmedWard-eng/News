package com.example.news.ui.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.repo.news.NewsRepo
import com.example.news.data.repo.news.NewsRepoImp
import com.example.news.domin.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsDetailsViewModel(private val repo: NewsRepo = NewsRepoImp()) : ViewModel() {

    private var isFavMutableData = MutableStateFlow<Boolean>(false)
    var isFavData = isFavMutableData

    private var signUpMutableSuccess = MutableStateFlow<Boolean?>(false)
    var signUpSuccess = signUpMutableSuccess

    private var signUpMutableError = MutableStateFlow<String?>(null)
    var signUpError = signUpMutableError

    fun deleteFromFav(currentNew: News) {
        viewModelScope.launch {
            repo.removeFromFavorites(currentNew)
        }
    }

    fun addToFav(currentNew: News) {
        viewModelScope.launch {
            var result = repo.addToFavorites(currentNew)
        }
    }

    fun getNewByTitle(currentNew: News) {
        viewModelScope.launch {
            val new = repo.getFavNewsWithTitle(currentNew.title)
            isFavMutableData.value = new != null
        }
    }

    fun changeFavIcon(currentNew: News) {
        if (isFavMutableData.value) {
            deleteFromFav(currentNew)
        } else {
            addToFav(currentNew)
        }
        isFavMutableData.value = !isFavMutableData.value
    }

}