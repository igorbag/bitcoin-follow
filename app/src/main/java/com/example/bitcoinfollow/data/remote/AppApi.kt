package com.example.bitcoinfollow.data.remote

import com.example.bitcoinfollow.model.bitcoin.BitcoinInfo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AppApi {

    @GET("charts/market-price?cors=true&timespan=30days&format=json&lang=pt")
    fun getBitcoinMarketPriceChart(): Deferred<BitcoinInfo>


}