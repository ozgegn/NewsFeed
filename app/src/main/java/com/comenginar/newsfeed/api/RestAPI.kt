package com.comenginar.newsfeed.api

import com.comenginar.newsfeed.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAPI {

    @GET("/v2/top-headlines")
    fun getGoogleNews(@Query("sources") path: String, @Query("apiKey") apiKey: String): Call<NewsResponse>

}