package com.comenginar.newsfeed.data

import com.comenginar.newsfeed.api.RestAPI
import com.comenginar.newsfeed.base.BaseRepository
import com.comenginar.newsfeed.model.Article

class NewsFeedRepository(private val api: RestAPI) : BaseRepository() {

    suspend fun getAllGoogleNews(path: String, apiKey: String): MutableList<Article>? {

        val newsFeedResponse = safeApiCall(
            call = { api.getGoogleNews(path, apiKey).await() },
            errorMessage = "Error Fetching News Feed"
        )

        return newsFeedResponse?.articles?.toMutableList()

    }

}