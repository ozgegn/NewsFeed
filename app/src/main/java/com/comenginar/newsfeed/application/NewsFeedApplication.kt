package com.comenginar.newsfeed.application

import android.app.Application
import com.comenginar.newsfeed.di.appModule
import com.comenginar.newsfeed.di.networkModule
import com.comenginar.newsfeed.di.repositoryModule
import com.comenginar.newsfeed.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsFeedApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            //Koin Android Logger
            androidLogger(Level.DEBUG)

            //Inject Android Context
            androidContext(this@NewsFeedApplication)

            //Use Modules
            modules(listOf(appModule, networkModule, viewModelModule, repositoryModule))
        }

    }

}