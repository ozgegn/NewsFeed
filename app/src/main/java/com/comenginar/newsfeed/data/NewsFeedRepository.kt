package com.comenginar.newsfeed.data

import androidx.lifecycle.MutableLiveData
import com.comenginar.newsfeed.api.RestAPI
import com.comenginar.newsfeed.base.BaseRepository
import com.comenginar.newsfeed.model.Article
import com.comenginar.newsfeed.model.NewsResponse

class NewsFeedRepository(private val api: RestAPI) : BaseRepository() {

    suspend fun getAllGoogleNews(path: String, apiKey: String): MutableList<Article>? {

        val newsFeedResponse = safeApiCall(
            call = { api.getGoogleNews(path, apiKey).await() },
            errorMessage = "Error Fetching News Feed"
        )

        return newsFeedResponse?.articles?.toMutableList()

    }

}