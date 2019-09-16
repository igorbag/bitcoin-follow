package com.example.bitcoinfollow.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.bitcoinfollow.model.bitcoin.BitcoinValue

@Dao
interface BitcoinDao {
    @Insert(onConflict = REPLACE)
    suspend fun save(entity: BitcoinValue)

    @Delete
    suspend fun delete(entity: BitcoinValue)

    @Query("DELETE FROM BitcoinValue")
    fun removeAll()

    @Query("SELECT * FROM BitcoinValue")
    fun findAll(): LiveData<BitcoinValue>
}