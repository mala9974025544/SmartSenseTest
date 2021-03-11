package com.smartsense.test

import android.app.Application
import android.content.Context
import android.util.Log
import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.pbreakers.mobile.androidtest.udacity.app.di.*



class TestApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Log.d("mala","TestApplication")
        startKoin {
            androidContext(this@TestApplication)
           androidLogger(Level.DEBUG)
            modules(listOf(viewModelModule, repositoryModule, netModule, apiModule, databaseModule))
        }
    }



}
