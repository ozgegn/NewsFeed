package com.comenginar.newsfeed.ui.allnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comenginar.newsfeed.BuildConfig
import com.comenginar.newsfeed.data.AllNewsRepository
import com.comenginar.newsfeed.model.Article
import kotlinx.coroutines.launch

class AllNewsViewModel(private val repository: AllNewsRepository) : ViewModel() {

    private val _data = MutableLiveData<MutableList<Article>>()
    val data: LiveData<MutableList<Article>>
        get() = _data

    private fun fetchData() {

        viewModelScope.launch {
            val allNews = repository.getAllNews("android", BuildConfig.API_KEY, "10", "1")
            _data.postValue(allNews)
        }

    }

}