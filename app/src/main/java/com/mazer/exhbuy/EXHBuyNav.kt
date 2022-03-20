package com.mazer.exhbuy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class EXHBuyNav(
    val route: String,
    val icon: ImageVector? = null
) {
    //App navigation
    object ACCOUNT : EXHBuyNav(
        route = "account",
        icon = Icons.Default.Face
    )

    object CREATING : EXHBuyNav(
        route = "creating",
        icon = Icons.Default.Add
    )

    object EXHIBITION : EXHBuyNav(
        route = "exhibition"
    )

    object SALE : EXHBuyNav(
        route = "sale",
        icon = Icons.Default.ShoppingCart
    )

    object FAVORITE : EXHBuyNav(
        route = "favorite"
    )

    object HISTORY : EXHBuyNav(
        route = "history"
    )

    object HOME : EXHBuyNav(
        route = "home",
        icon = Icons.Default.Home
    )

    object REGISTRATION : EXHBuyNav(
        route = "registration"
    )

    object SETTINGS : EXHBuyNav(
        route = "settings"
    )

    object SEARCHING : EXHBuyNav(
        route = "searching"
    )

    //SignIn navigation
    object SIGNINCHOOSE: EXHBuyNav(
        route = "signInChoose"
    )

    object GOOGLESIGNIN: EXHBuyNav(
        route = "googleSignIn"
    )

    object PHONESIGNIN: EXHBuyNav(
        route = "phoneSignIn"
    )
}