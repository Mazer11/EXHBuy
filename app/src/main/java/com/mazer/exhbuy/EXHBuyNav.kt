package com.mazer.exhbuy

sealed class EXHBuyNav(
    val route: String
){
    object ACCOUNT : EXHBuyNav(route = "account")
    object AUTHENTICATION : EXHBuyNav(route = "authentication")
    object CREATING : EXHBuyNav(route = "creating")
    object EXHIBITION : EXHBuyNav(route = "exhibition")
    object SALE : EXHBuyNav(route = "sale")
    object FAVORITE : EXHBuyNav(route = "favorite")
    object HISTORY : EXHBuyNav(route = "history")
    object HOME : EXHBuyNav(route = "home")
    object REGISTRATION : EXHBuyNav(route = "registration")
    object SETTINGS : EXHBuyNav(route = "settings")
    object SEARCHING : EXHBuyNav(route = "searching")
}