package com.smartsense.test

import android.app.Application
import android.util.Log
import com.smartsense.test.di.*
import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin




class TestApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApplication)
           androidLogger(Level.DEBUG)
            modules(listOf(viewModelModule, repositoryModule, netModule, apiModule, databaseModule))
        }
    }



}
