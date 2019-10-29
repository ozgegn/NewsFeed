package com.comenginar.newsfeed.di

import com.comenginar.newsfeed.BuildConfig
import com.comenginar.newsfeed.api.RestAPI
import com.comenginar.newsfeed.data.AllNewsRepository
import com.comenginar.newsfeed.data.NewsFeedRepository
import com.comenginar.newsfeed.ui.allnews.AllNewsViewModel
import com.comenginar.newsfeed.ui.newsfeed.NewsFeedViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * DI  Module
 * TODO : Dependencies are here

 */

val appModule = module {

}

val viewModelModule = module {

    viewModel {
        NewsFeedViewModel(get())
    }
    viewModel {
        AllNewsViewModel(get())
    }

}

val repositoryModule = module {

    single {
        NewsFeedRepository(get())
    }
    single {
        AllNewsRepository(get())
    }

}

val networkModule = module {

    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(
            CoroutineCallAdapterFactory()
        ).build()

    fun provideNewsFeedApi(retrofit: Retrofit): RestAPI = retrofit.create(RestAPI::class.java)


    single<Retrofit> {
        provideRetrofit()
    }

    single {
        provideNewsFeedApi(get())
    }

}

