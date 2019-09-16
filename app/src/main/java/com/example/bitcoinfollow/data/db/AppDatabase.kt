package com.example.bitcoinfollow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitcoinfollow.data.db.dao.BitcoinDao
import com.example.bitcoinfollow.model.bitcoin.BitcoinValue

@Database(entities = [BitcoinValue::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bitcoinDao(): BitcoinDao

}