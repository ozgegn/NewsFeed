package com.comenginar.newsfeed.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.comenginar.newsfeed.api.RestAPI
import com.comenginar.newsfeed.base.BaseRepository
import com.comenginar.newsfeed.data.remote.AllNewsDataSourceFactory
import com.comenginar.newsfeed.data.remote.AllNewsRemoteDataSource
import com.comenginar.newsfeed.model.Article
import kotlinx.coroutines.CoroutineScope

class AllNewsRepository(private val allNewsRemoteDataSource: AllNewsRemoteDataSource) : BaseRepository() {

    fun observeRemotePagedSets(
        scope: CoroutineScope,
        apiKey: String,
        query: String
    ): LiveData<PagedList<Article>> {
        val dataSourceFactory = AllNewsDataSourceFactory(
            repository = this,
            scope = scope,
            apiKey = apiKey,
            query = query,
            allNewsRemoteDataSource = allNewsRemoteDataSource
        )

        return LivePagedListBuilder(
            dataSourceFactory,
            AllNewsDataSourceFactory.pagedListConfig()
        ).build()

    }

}