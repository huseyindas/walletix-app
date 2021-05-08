package com.huseyindas.walletixapp.database

import androidx.lifecycle.LiveData

class WalletRepo(private val walletDao: WalletDao) {

    val getAll: LiveData<List<Wallet>> = walletDao.getAll()

    suspend fun addExpense(wallet: Wallet) {
        walletDao.add(wallet)
    }

    suspend fun deleteExp(wallet: Wallet) {
        walletDao.deleteExp(wallet)
    }

}