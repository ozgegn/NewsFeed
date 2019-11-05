package com.comenginar.newsfeed.data.remote

import com.comenginar.newsfeed.api.RestAPI
import com.comenginar.newsfeed.base.BaseRepository

class AllNewsRemoteDataSource(private val api: RestAPI) : BaseRepository() {

    suspend fun getAllNews(
        category: String,
        apiKey: String,
        perPage: Int,
        page: Int
    ) = safeApiCall(
        call = { api.getAllNews(category, apiKey, perPage, page).await() },
        errorMessage = "Error Fetching All News"
    )

}