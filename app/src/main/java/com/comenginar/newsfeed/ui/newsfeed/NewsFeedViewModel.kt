package com.comenginar.newsfeed.ui.newsfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.comenginar.newsfeed.BuildConfig
import com.comenginar.newsfeed.data.NewsFeedRepository
import com.comenginar.newsfeed.model.Article
import com.comenginar.newsfeed.model.LoadingState
import com.comenginar.newsfeed.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFeedViewModel(private val repository: NewsFeedRepository) : ViewModel(),
    Callback<NewsResponse> {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<List<Article>>()
    val data: LiveData<List<Article>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        _loadingState.postValue(LoadingState.LOADING)
        repository.getAllGoogleNews("google-news", BuildConfig.API_KEY).enqueue(this)
    }

    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
    }

    override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
        if (response.isSuccessful) {
            _data.postValue(response.body()?.articles)
            _loadingState.postValue(LoadingState.LOADED)
        } else
            _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
    }

}