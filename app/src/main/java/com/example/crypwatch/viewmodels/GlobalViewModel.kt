package com.example.crypwatch.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypwatch.models.CoinDetail
import com.example.crypwatch.models.GlobalData
import com.example.crypwatch.repository.CrypWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlobalViewModel @Inject constructor(private val repository: CrypWatchRepository) : ViewModel() {

    val Global : StateFlow<GlobalData>
        get() = repository.global

    init {
        viewModelScope.launch {
            repository.getGlobal()
        }
    }

}