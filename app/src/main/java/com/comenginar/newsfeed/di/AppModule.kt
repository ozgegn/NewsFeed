package com.comenginar.newsfeed.di

import com.comenginar.newsfeed.BuildConfig
import com.comenginar.newsfeed.api.RestAPI
import com.comenginar.newsfeed.data.NewsFeedRepository
import com.comenginar.newsfeed.ui.newsfeed.NewsFeedViewModel
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

}

val repositoryModule = module {

    single {
        NewsFeedRepository(get())
    }

}

val networkModule = module {

    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(MoshiConverterFactory.create()).build()

    fun provideNewsFeedApi(retrofit: Retrofit): RestAPI = retrofit.create(RestAPI::class.java)


    single<Retrofit> {
        provideRetrofit()
    }

    single {
        provideNewsFeedApi(get())
    }

}

