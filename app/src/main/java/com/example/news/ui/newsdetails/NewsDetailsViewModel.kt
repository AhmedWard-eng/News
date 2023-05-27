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
            var result = repo.removeFromFavorites(currentNew)
            if (result.isSuccess) {
                signUpMutableSuccess.value = true
            } else {
                result.onFailure {
                    signUpMutableError.value = it.message ?: ""

                }
            }
        }
    }

    fun addToFav(currentNew: News) {
        viewModelScope.launch {
            var result = repo.addToFavorites(currentNew)
            if (result.isSuccess) {
                signUpMutableSuccess.value = true
            } else {
                result.onFailure {
                    signUpMutableError.value = it.message ?: ""

                }
            }
        }
    }

    fun getNewByTitle(currentNew: News) {
        viewModelScope.launch {
            val new = repo.getFavNewsWithTitle(currentNew.title)
            isFavMutableData.value = new != null
        }
    }

    fun changeFavIcon() {
        isFavMutableData.value = !isFavMutableData.value
    }

}