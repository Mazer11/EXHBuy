package com.mazer.exhbuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.core.app.ComponentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mazer.exhbuy.EXHBuyApp
import com.mazer.exhbuy.ui.screens.acconunt.AccountScreen
import com.mazer.exhbuy.ui.screens.acconunt.FavoriteScreen
import com.mazer.exhbuy.ui.screens.acconunt.HistoryScreen
import com.mazer.exhbuy.ui.screens.exdetails.ExhibitionScreen
import com.mazer.exhbuy.ui.screens.home.CreatingScreen
import com.mazer.exhbuy.ui.screens.home.HomeScreen
import com.mazer.exhbuy.ui.screens.home.SearchingScreen
import com.mazer.exhbuy.ui.screens.login.LogInScreen
import com.mazer.exhbuy.ui.screens.login.RegistrationScreen
import com.mazer.exhbuy.ui.screens.settings.SettingsScreen
import com.mazer.exhbuy.ui.screens.shoppongcart.SaleScreen
import com.mazer.exhbuy.viewmodels.LoginVM

@Composable
fun NavGraph(
    navController: NavHostController,
    activity: ComponentActivity,
    application: EXHBuyApp
) {

    NavHost(
        navController = navController,
        startDestination = NavigationRouts.LOGIN.route
    ) {
        composable(
            route = NavigationRouts.ACCOUNT.route
        ) {
            AccountScreen(
                navController = navController
            )
        }

        composable(
            route = NavigationRouts.LOGIN.route
        ) {
            val vm = hiltViewModel<LoginVM>()
            LogInScreen(
                navController = navController,
                vm = vm
            )
        }

        composable(
            route = NavigationRouts.REGISTRATION.route
        ) {
            val vm = hiltViewModel<LoginVM>()
            RegistrationScreen(
                vm = vm,
                activity = activity,
                navController = navController
            )
        }

        composable(
            route = NavigationRouts.SALE.route
        ) {
            SaleScreen()
        }

        composable(
            route = NavigationRouts.HOME.route
        ) {
            val vm = hiltViewModel<LoginVM>()
            HomeScreen(
                navController = navController
            )
        }

        composable(
            route = NavigationRouts.SETTINGS.route
        ) {
            SettingsScreen()
        }

        composable(
            route = NavigationRouts.CREATING.route
        ) {
            CreatingScreen()
        }

        composable(
            route = NavigationRouts.EXHIBITION.route
        ) {
            ExhibitionScreen()
        }
        composable(
            route = NavigationRouts.FAVORITE.route
        ) {
            FavoriteScreen()
        }
        composable(
            route = NavigationRouts.HISTORY.route
        ) {
            HistoryScreen()
        }
        composable(
            route = NavigationRouts.SEARCHING.route
        ) {
            SearchingScreen()
        }
    }
}