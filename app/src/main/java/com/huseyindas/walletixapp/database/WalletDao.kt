 package com.huseyindas.walletixapp.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WalletDao {

    @Insert
    suspend fun add(expense : Wallet)

    @Query( "SELECT * FROM wallet_db ORDER BY num DESC")
    fun getAll() : LiveData<List<Wallet>>

    @Query( "SELECT * FROM wallet_db ORDER BY num DESC LIMIT 1")
    suspend fun getLast() : Wallet?

    @Query( "SELECT * FROM wallet_db WHERE num = :id")
    suspend fun getId(id : Int) : Wallet?

    @Query("DELETE FROM wallet_db WHERE num = :id")
    suspend fun deleteExpense(id: Int?)

    @Delete
    suspend fun deleteExp(wallet: Wallet)

}