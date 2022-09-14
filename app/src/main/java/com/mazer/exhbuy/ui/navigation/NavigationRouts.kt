package com.mazer.exhbuy.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationRouts(
    val route: String,
    val icon: ImageVector? = null
) {
    //App navigation
    object ACCOUNT : NavigationRouts(
        route = "account",
        icon = Icons.Default.Face
    )

    object CREATING : NavigationRouts(
        route = "creating",
        icon = Icons.Default.Add
    )

    object EXHIBITION : NavigationRouts(
        route = "exhibition"
    )

    object SALE : NavigationRouts(
        route = "sale",
        icon = Icons.Default.ShoppingCart
    )

    object FAVORITE : NavigationRouts(
        route = "favorite"
    )

    object HISTORY : NavigationRouts(
        route = "history"
    )

    object HOME : NavigationRouts(
        route = "home",
        icon = Icons.Default.Home
    )

    object REGISTRATION : NavigationRouts(
        route = "registration"
    )

    object SETTINGS : NavigationRouts(
        route = "settings"
    )

    object SEARCHING : NavigationRouts(
        route = "searching"
    )

    object LOGIN: NavigationRouts(
        route = "login"
    )
}