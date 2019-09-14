package com.example.bitcoinfollow.data.repository.bitcoin

import com.example.bitcoinfollow.model.bitcoin.BitcoinInfo

interface BitcoinRepository {
    suspend fun getBitcoinMarketPriceChart(): BitcoinInfo
}