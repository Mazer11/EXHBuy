package com.mazer.exhbuy

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mazer.exhbuy.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = EXHBuyNav.HOME.route
    ) {
        composable(route = EXHBuyNav.ACCOUNT.route) {
            AccountScreen()
        }

        composable(route = EXHBuyNav.SALE.route){
            SaleScreen()
        }

        composable(route = EXHBuyNav.AUTHENTICATION.route) {
            AuthenticationScreen()
        }

        composable(route = EXHBuyNav.HOME.route) {
            HomeScreen()
        }

        composable(route = EXHBuyNav.SETTINGS.route){
            SettingsScreen()
        }

        composable(route = EXHBuyNav.CREATING.route) {
            CreatingScreen()
        }

        composable(route = EXHBuyNav.EXHIBITION.route) {
            ExhibitionScreen()
        }
        composable(route = EXHBuyNav.FAVORITE.route) {
            FavoriteScreen()
        }
        composable(route = EXHBuyNav.HISTORY.route) {
            HistoryScreen()
        }
        composable(route = EXHBuyNav.REGISTRATION.route) {
            RegistrationScreen()
        }
        composable(route = EXHBuyNav.SEARCHING.route) {
            SearchingScreen()
        }
    }
}