package com.example.bitcoinfollow.di

import androidx.room.Room
import com.example.bitcoinfollow.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            getProperty("DB_NAME")
        ).build()
    }

    single { get<AppDatabase>().bitcoinDao() }

}