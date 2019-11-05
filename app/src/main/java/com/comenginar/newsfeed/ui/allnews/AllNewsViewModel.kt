package com.comenginar.newsfeed.ui.allnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comenginar.newsfeed.BuildConfig
import com.comenginar.newsfeed.data.AllNewsRepository

class AllNewsViewModel(private val repository: AllNewsRepository) : ViewModel() {

    val data by lazy {
        repository.observeRemotePagedSets(viewModelScope, BuildConfig.API_KEY, "android")
    }
}

