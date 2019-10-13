package com.comenginar.newsfeed.data

import com.comenginar.newsfeed.api.RestAPI

class NewsFeedRepository(private val api: RestAPI) {

    fun getAllGoogleNews(path: String, apiKey: String) = api.getGoogleNews(path, apiKey)

}