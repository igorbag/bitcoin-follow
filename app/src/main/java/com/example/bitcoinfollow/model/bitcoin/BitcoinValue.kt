package com.example.bitcoinfollow.model.bitcoin

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(primaryKeys = ["x"])
@Parcelize
data class BitcoinValue(
    @field:SerializedName("x") val x: Int,
    @field:SerializedName("y") val y: Float
) : Parcelable