package com.comenginar.newsfeed.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "status") var status: String?,
    @Json(name = "totalResults") var totalResults: Int?,
    @Json(name = "articles") var articles: List<Article>
)