package com.mazer.exhbuy.data.db

sealed class DatabaseReferences(val reference: String){
    object EVENTS: DatabaseReferences("Events")
}