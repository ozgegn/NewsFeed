package com.comenginar.newsfeed.data

import com.comenginar.newsfeed.api.RestAPI
import com.comenginar.newsfeed.base.BaseRepository
import com.comenginar.newsfeed.model.Article

class AllNewsRepository(private val api: RestAPI) : BaseRepository() {

    suspend fun getAllNews(
        category: String,
        apiKey: String,
        pageSize: String,
        page: String
    ): MutableList<Article>? {

        val allNewsResponse = safeApiCall(
            call = { api.getAllNews(category, apiKey, pageSize, page).await() },
            errorMessage = "Error Fetching All News"
        )

        return allNewsResponse?.articles?.toMutableList()

    }

}