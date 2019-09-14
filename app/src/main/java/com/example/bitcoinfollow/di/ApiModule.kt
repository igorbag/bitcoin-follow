package com.example.bitcoinfollow.di

import com.example.bitcoinfollow.data.remote.AppApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single(createdAtStart = false) { get<Retrofit>().create(AppApi::class.java) }

}