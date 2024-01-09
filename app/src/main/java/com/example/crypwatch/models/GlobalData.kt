package com.example.crypwatch.models

data class GlobalData(
    val market_cap_usd: Long?,
    val volume_24h_usd: Long?,
    val bitcoin_dominance_percentage: Double?,
    val cryptocurrencies_number: Int?,
    val last_updated: Int?,
)