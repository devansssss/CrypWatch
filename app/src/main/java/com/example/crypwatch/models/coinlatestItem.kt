package com.example.crypwatch.models

data class coinlatestItem(
    val close: Double,
    val high: Double,
    val low: Double,
    val market_cap: Long,
    val `open`: Double,
    val time_close: String,
    val time_open: String,
    val volume: Long
)