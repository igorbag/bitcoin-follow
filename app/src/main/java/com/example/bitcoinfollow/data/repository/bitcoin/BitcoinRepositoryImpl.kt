package com.example.bitcoinfollow.data.repository.bitcoin

import com.example.bitcoinfollow.data.remote.AppApi
import com.example.bitcoinfollow.data.repository.BaseRepository
import com.example.bitcoinfollow.model.bitcoin.BitcoinInfo

class BitcoinRepositoryImpl(private val api: AppApi) : BaseRepository(), BitcoinRepository {

    override suspend fun getBitcoinMarketPriceChart(): BitcoinInfo {
        return super.handleAsyncCall(api.getBitcoinMarketPriceChart())
    }

}