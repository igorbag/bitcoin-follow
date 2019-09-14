package com.example.bitcoinfollow.model.bitcoin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BitcoinValue(
    val x: Int,
    val y: Float
) : Parcelable