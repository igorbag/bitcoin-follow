package com.example.bitcoinfollow.di

import com.example.bitcoinfollow.data.repository.bitcoin.BitcoinRepository
import com.example.bitcoinfollow.data.repository.bitcoin.BitcoinRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<BitcoinRepository> { BitcoinRepositoryImpl(get()) }

}