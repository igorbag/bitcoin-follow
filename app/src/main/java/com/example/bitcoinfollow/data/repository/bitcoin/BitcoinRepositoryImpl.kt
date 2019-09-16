package com.example.bitcoinfollow.data.repository.bitcoin

import androidx.lifecycle.LiveData
import com.example.bitcoinfollow.data.db.dao.BitcoinDao
import com.example.bitcoinfollow.data.remote.AppApi
import com.example.bitcoinfollow.data.repository.BaseRepository
import com.example.bitcoinfollow.model.bitcoin.BitcoinInfo
import com.example.bitcoinfollow.model.bitcoin.BitcoinValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BitcoinRepositoryImpl(private val api: AppApi, private val bitcoinDao: BitcoinDao) :
    BaseRepository(), BitcoinRepository {

    override suspend fun getBitcoinMarketPriceChart(): BitcoinInfo {
        return super.handleAsyncCall(api.getBitcoinMarketPriceChart())
    }

    override fun save(entity: List<BitcoinValue>) {
        runBlocking {
            launch(Dispatchers.IO) {
                bitcoinDao.save(entity)
            }
        }

    }

    override fun delete(entity: BitcoinValue) {
        runBlocking {
            launch(Dispatchers.IO) {
                bitcoinDao.delete(entity)
            }
        }
    }

    override fun removeAll() {
        runBlocking {
            launch(Dispatchers.IO) {
                bitcoinDao.removeAll()
            }
        }
    }

    override fun findAll(): LiveData<List<BitcoinValue>> {
        return bitcoinDao.findAll()
    }

}