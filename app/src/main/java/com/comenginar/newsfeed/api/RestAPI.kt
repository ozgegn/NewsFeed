package com.comenginar.newsfeed.api

import com.comenginar.newsfeed.model.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAPI {

    @GET("/v2/top-headlines")
    fun getGoogleNews(@Query("sources") path: String, @Query("apiKey") apiKey: String): Deferred<Response<NewsResponse>>

}