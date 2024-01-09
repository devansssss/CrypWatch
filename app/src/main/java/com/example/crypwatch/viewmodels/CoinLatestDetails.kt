package com.example.crypwatch.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypwatch.models.coinlatestItem
import com.example.crypwatch.repository.CrypWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinLatestDetails @Inject constructor(private val repository: CrypWatchRepository, private val savedStateHandle: SavedStateHandle) : ViewModel(){

    val coinlatest : StateFlow<List<coinlatestItem>>
        get() = repository.coinlatest

    init {
        viewModelScope.launch {
            val coinID = savedStateHandle.get<String>("coins") ?: "btc-bitcoin"
            repository.getCoinLatest(coinID)
        }
    }
}