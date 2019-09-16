package com.example.bitcoinfollow

import android.app.Application
import com.example.bitcoinfollow.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialising Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        //Initialising Koin
        startKoin {
            androidContext(this@App)
            modules(apiModule, databaseModule, networkModule, repositoryModule, viewModelModule)
            androidFileProperties()
        }
    }
}