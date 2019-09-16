package com.example.bitcoinfollow.data.repository.bitcoin

import androidx.lifecycle.LiveData
import com.example.bitcoinfollow.model.bitcoin.BitcoinInfo
import com.example.bitcoinfollow.model.bitcoin.BitcoinValue

interface BitcoinRepository {
    suspend fun getBitcoinMarketPriceChart(): BitcoinInfo

    fun save(entity: BitcoinValue)
    fun delete(entity: BitcoinValue)
    fun removeAll()
    fun findAll(): LiveData<BitcoinValue>
}