package com.example.bitcoinfollow.data.repository

import com.example.bitcoinfollow.utils.BusinessException
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.HttpException

open class BaseRepository {

    companion object {
        const val BUSINESS_MESSAGE_ATTR = "err"
    }

    suspend fun <T : Any> handleAsyncCall(asyncCall: Deferred<T>): T {
        try {
            return asyncCall.await()
        } catch (e: HttpException) {
            try {
                val jsonErrorBody = JSONObject(e.response().errorBody()?.string())
                throw BusinessException(jsonErrorBody.getString(BUSINESS_MESSAGE_ATTR), e)
            } catch (e: Throwable) {
                when (e) {
                    is BusinessException -> throw e
                    else -> throw BusinessException(e)
                }
            }
        } catch (e: Throwable) {
            throw BusinessException(e)
        }
    }
}