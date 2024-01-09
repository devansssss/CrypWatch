package com.example.crypwatch.api

import com.example.crypwatch.models.CoinDetail
import com.example.crypwatch.models.CoinListItem
import com.example.crypwatch.models.GlobalData
import com.example.crypwatch.models.coinlatestItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CrypWatchAPI {

    @GET("/v1/coins")
    suspend fun getCoins(): Response<List<CoinListItem>>


    @GET("/v1/coins/{coinID}")
    suspend fun getCoin(@Path("coinID") coinID: String): Response<CoinDetail>

    @GET("/v1/global")
    suspend fun getGlobal() : Response<GlobalData>

    @GET("/v1/coins/{coinID}/ohlcv/latest")
    suspend fun getCoinLatest(@Path("coinID") coinID: String) : Response<List<coinlatestItem>>

}