package com.example.crypwatch.repository

import android.util.Log
import com.example.crypwatch.api.CrypWatchAPI
import com.example.crypwatch.models.CoinDetail
import com.example.crypwatch.models.CoinListItem
import com.example.crypwatch.models.GlobalData
import com.example.crypwatch.models.coinlatestItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CrypWatchRepository @Inject constructor(private val crypWatchAPI: CrypWatchAPI) {


    private val _coins = MutableStateFlow<List<CoinListItem>>(emptyList())
    val coins: StateFlow<List<CoinListItem>>
        get() = _coins



    private val _coinDetail = MutableStateFlow<CoinDetail?>(CoinDetail("","","",false,"","",true,true,"","","","",true,"","",0,"","",""))
    val coinDetail: StateFlow<CoinDetail?>
        get() = _coinDetail


    private val _global = MutableStateFlow<GlobalData>(GlobalData(0,0,0.00,0,0))
    val global: StateFlow<GlobalData>
        get() = _global


    private val _coinlatest = MutableStateFlow<List<coinlatestItem>>(emptyList())
    val coinlatest : StateFlow<List<coinlatestItem>>
        get() = _coinlatest


    suspend fun getCoins(){
        val response = crypWatchAPI.getCoins()
        if(response.isSuccessful && response.body() != null){
            _coins.emit(response.body()!!)
        }
    }




    suspend fun getCoinDetail(coin: String){
        val response = crypWatchAPI.getCoin(coin)
        if(response.isSuccessful && response.body() != null){
            _coinDetail.emit(response.body()!!)
        }
    }


    suspend fun getGlobal(){
        val response = crypWatchAPI.getGlobal()
        if (response.isSuccessful && response.body()!=null){
            _global.emit(response.body()!!)
        }
    }

    suspend fun getCoinLatest(coin : String){
        val response = crypWatchAPI.getCoinLatest(coin)
        if(response.isSuccessful && response.body() != null){
            _coinlatest.emit(response.body()!!)
        }
    }
}