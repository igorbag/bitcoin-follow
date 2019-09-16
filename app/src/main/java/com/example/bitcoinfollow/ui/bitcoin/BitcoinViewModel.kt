package com.example.bitcoinfollow.ui.bitcoin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitcoinfollow.data.repository.bitcoin.BitcoinRepository
import com.example.bitcoinfollow.model.bitcoin.BitcoinInfo
import com.example.bitcoinfollow.model.bitcoin.BitcoinValue
import com.example.bitcoinfollow.utils.BusinessException
import com.example.bitcoinfollow.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BitcoinViewModel(
    private val bitcoinRepository: BitcoinRepository
) : ViewModel() {

    private val viewModelState: MutableLiveData<Result<BitcoinInfo>> = MutableLiveData()


    fun getBitcoinMarketPriceChart(): MutableLiveData<Result<BitcoinInfo>> {
        viewModelState.value = Result.Loading()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val bitcoin = bitcoinRepository.getBitcoinMarketPriceChart()
                viewModelState.value = Result.Success(bitcoin)
            } catch (e: BusinessException) {
                viewModelState.value = Result.Error(e.message)
            }
        }
        return viewModelState
    }


    fun save(entity: BitcoinValue) {
        return bitcoinRepository.save(entity)
    }

    fun delete(entity: BitcoinValue) {
        return bitcoinRepository.delete(entity)
    }

    fun removeAll(entity: BitcoinValue) {
        return bitcoinRepository.removeAll()
    }


    fun findAll(): LiveData<BitcoinValue> {
        return bitcoinRepository.findAll()
    }


}