package com.example.crypwatch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypwatch.models.CoinListItem
import com.example.crypwatch.repository.CrypWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinsViewModel @Inject constructor(private val repository: CrypWatchRepository) : ViewModel() {

    val coins : StateFlow<List<CoinListItem>>
        get() = repository.coins

    init {
        viewModelScope.launch {
            repository.getCoins()
        }
    }
}