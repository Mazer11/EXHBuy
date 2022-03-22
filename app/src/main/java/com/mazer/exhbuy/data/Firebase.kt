package com.mazer.exhbuy.data

sealed class DatabaseReferences(val reference: String){
    object EVENTS: DatabaseReferences("Events")
}