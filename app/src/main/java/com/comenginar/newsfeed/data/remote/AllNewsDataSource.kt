package com.comenginar.newsfeed.data.remote

import androidx.paging.PageKeyedDataSource
import com.comenginar.newsfeed.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AllNewsDataSource(
    private val dataSource: AllNewsRemoteDataSource,
    private val query: String,
    private val apiKey: String,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Article>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        executeQuery(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        val page = params.key
        executeQuery(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        val page = params.key
        executeQuery(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun executeQuery(page: Int, perPage: Int, callback: (List<Article>) -> Unit) {

        scope.launch {
            val result = dataSource.getAllNews(query, apiKey, perPage, page)
            if (result != null)
                callback(result.articles)
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}