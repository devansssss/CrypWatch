package com.example.crypwatch.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypwatch.models.CoinDetail
import com.example.crypwatch.repository.CrypWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: CrypWatchRepository,
                                          private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val coindetail : StateFlow<CoinDetail?>
        get() = repository.coinDetail

    init {
        viewModelScope.launch {
            val coinID = savedStateHandle.get<String>("coins") ?: "btc-bitcoin"
            repository.getCoinDetail(coinID)
        }
    }

}