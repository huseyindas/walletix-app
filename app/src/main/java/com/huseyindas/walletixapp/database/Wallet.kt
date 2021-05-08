package com.huseyindas.walletixapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "wallet_db")
data class Wallet(

    @PrimaryKey(autoGenerate = true)
    var num: Int,
    var explain: String,
    var amount: Int,
    var category: String,
    var rate: String,

):Parcelable