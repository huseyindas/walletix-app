package com.huseyindas.walletixapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Wallet::class], version = 2, exportSchema = false)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun walletDao(): WalletDao

    companion object {

        @Volatile
        private var INSTANCE: WalletDatabase? = null

        fun getDatabase(context: Context): WalletDatabase {

            val instance = INSTANCE
            if (instance != null) {
                return instance
            }

            synchronized(this) {
                    val sample = Room.databaseBuilder(
                            context.applicationContext,
                            WalletDatabase::class.java,
                            "wallet_db"
                    ).build()

                    INSTANCE = sample

                return sample
            }
        }
    }

}