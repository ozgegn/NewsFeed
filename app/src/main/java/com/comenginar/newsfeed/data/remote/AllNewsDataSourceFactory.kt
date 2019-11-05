package com.comenginar.newsfeed.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.comenginar.newsfeed.data.AllNewsRepository
import com.comenginar.newsfeed.model.Article
import kotlinx.coroutines.CoroutineScope

class AllNewsDataSourceFactory(
    private val repository: AllNewsRepository,
    private var query: String = "",
    private val apiKey: String,
    private val scope: CoroutineScope,
    private val allNewsRemoteDataSource: AllNewsRemoteDataSource
) : DataSource.Factory<Int, Article>() {

    private val source = MutableLiveData<AllNewsDataSource>()

    override fun create(): DataSource<Int, Article> {
        val source = AllNewsDataSource(allNewsRemoteDataSource, query, apiKey, scope)
        this.source.postValue(source)
        return source
    }

    companion object {

        private const val PAGE_SIZE = 20

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()

    }
}