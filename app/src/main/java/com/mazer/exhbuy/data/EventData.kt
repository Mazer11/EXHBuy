package com.mazer.exhbuy.data

data class EventData(
    val eventName: String,
    val location: String,
    val ticketsCount: Int,
    val startDate: String,
    val endDate: String,
    val price: Int
)