package com.example.bitcoinfollow.model.bitcoin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BitcoinInfo(
    val status: String,
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<BitcoinValue>
) : Parcelable