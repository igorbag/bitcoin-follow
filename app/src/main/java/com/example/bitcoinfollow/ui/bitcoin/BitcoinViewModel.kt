package com.example.bitcoinfollow.ui.bitcoin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitcoinfollow.data.repository.bitcoin.BitcoinRepository
import com.example.bitcoinfollow.model.bitcoin.BitcoinInfo
import com.example.bitcoinfollow.utils.BusinessException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.bitcoinfollow.utils.*

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


}