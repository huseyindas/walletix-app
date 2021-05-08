package com.huseyindas.walletixapp.api

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {
    @GET("/api/v7/convert?q=USD_TRY&compact=ultra&apiKey=88606544a6690a850463")
    fun getCurrencyDolar() : Call<Currency>
    @GET("/api/v7/convert?q=EUR_TRY&compact=ultra&apiKey=88606544a6690a850463")
    fun getCurrencyEuro() : Call<Currency>
    @GET("/api/v7/convert?q=GBP_TRY&compact=ultra&apiKey=88606544a6690a850463")
    fun getCurrencySterlin() : Call<Currency>
}