package com.mazer.exhbuy.data.model

data class EventData(
    var firestore_id: String = "",
    val event_name: String = "",
    val event_location: String = "",
    val date_of_start: String = "",
    val date_of_end: String = "",
    val description: String = "",
    val creating_date: String = "",
    val approved: Boolean = false
)