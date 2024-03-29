package com.mazer.exhbuy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.core.app.ComponentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.EXHBuyApp
import com.mazer.exhbuy.data.model.EventData
import com.mazer.exhbuy.ui.screens.acconunt.AccountScreen
import com.mazer.exhbuy.ui.screens.acconunt.FavoriteScreen
import com.mazer.exhbuy.ui.screens.acconunt.HistoryScreen
import com.mazer.exhbuy.ui.screens.exdetails.ExhibitionScreen
import com.mazer.exhbuy.ui.screens.home.HomeScreen
import com.mazer.exhbuy.ui.screens.home.SearchingScreen
import com.mazer.exhbuy.ui.screens.login.RegistrationScreen
import com.mazer.exhbuy.ui.screens.settings.SettingsScreen
import com.mazer.exhbuy.viewmodels.CreatingVM
import com.mazer.exhbuy.viewmodels.LoginVM

@Composable
fun NavGraph(
    navController: NavHostController,
    activity: ComponentActivity,
    application: EXHBuyApp,
    mAuth: FirebaseAuth
) {

    NavHost(
        navController = navController,
        startDestination = NavigationRouts.HOME.route
    ) {
        composable(
            route = NavigationRouts.ACCOUNT.route
        ) {
            AccountScreen(
                navController = navController,
                mAuth = mAuth
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
            route = NavigationRouts.HOME.route
        ) {
            val loginVM = hiltViewModel<LoginVM>()
            val creatingVM = hiltViewModel<CreatingVM>()
            HomeScreen(
                navController = navController,
                mAuth = mAuth,
                loginVM = loginVM,
                mainActivity = activity,
                creatingVM = creatingVM
            )
        }

        composable(
            route = NavigationRouts.SETTINGS.route
        ) {
            SettingsScreen(application = application, navController = navController)
        }

        composable(
            route = "${NavigationRouts.EXHIBITION.route}/{eventId}",
            arguments = listOf(navArgument("eventId"){ type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("eventId")?.let {
                ExhibitionScreen(
                    navController = navController,
                    event_id = it
                )
            }
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