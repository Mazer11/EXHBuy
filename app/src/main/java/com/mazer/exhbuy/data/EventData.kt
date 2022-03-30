package com.mazer.exhbuy.data

data class EventData(
    val eventName: String = "",
    val location: String = "",
    val ticketsCount: Int = -1,
    val startDate: String = "",
    val endDate: String = "",
    val price: Int = -1
)