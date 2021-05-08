package com.huseyindas.walletixapp

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.huseyindas.walletixapp.database.Wallet
import com.huseyindas.walletixapp.database.WalletDao
import com.huseyindas.walletixapp.database.WalletDatabase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var walletDao : WalletDao
    private lateinit var walletDatabase : WalletDatabase


    @Before
    fun createDatabase() {
        val connect = InstrumentationRegistry.getInstrumentation().targetContext

        walletDatabase = Room.inMemoryDatabaseBuilder(connect, WalletDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        walletDao = walletDatabase.walletDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        walletDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun addExpsenseAndRead() {
        runBlocking {
            val expense = Wallet()
            walletDao.add(expense)
            val getLast = walletDao.getLast()
            assertEquals(getLast?.rate,1)
        }
    }
}