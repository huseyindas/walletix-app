package com.huseyindas.walletixapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    val getAll: LiveData<List<Wallet>>
    private val dao = WalletDatabase.getDatabase(application).walletDao()
    val repository = WalletRepo(dao)

    init {
        getAll = repository.getAll
    }

    fun addExpense(wallet: Wallet) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpense(wallet)
        }
    }

    fun deleteExpense(wallet: Wallet) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExp(wallet)
        }
    }

}