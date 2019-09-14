package com.example.bitcoinfollow.di

import com.example.bitcoinfollow.ui.bitcoin.BitcoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { BitcoinViewModel(get()) }

}