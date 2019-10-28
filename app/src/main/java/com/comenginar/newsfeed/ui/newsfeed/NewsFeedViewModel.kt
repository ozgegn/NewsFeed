package com.comenginar.newsfeed.ui.newsfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comenginar.newsfeed.BuildConfig
import com.comenginar.newsfeed.data.NewsFeedRepository
import com.comenginar.newsfeed.model.Article
import com.comenginar.newsfeed.model.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewsFeedViewModel(private val repository: NewsFeedRepository) : ViewModel(){

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<MutableList<Article>>()
    val data: LiveData<MutableList<Article>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {

        viewModelScope.launch {
            val newsFeed = repository.getAllGoogleNews("google-news", BuildConfig.API_KEY)
            _loadingState.postValue(LoadingState.LOADING)
            _data.postValue(newsFeed)
        }

    }

}