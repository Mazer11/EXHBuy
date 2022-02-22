package com.mazer.exhbuy

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import com.mazer.exhbuy.ui.components.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EXHBuyApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavGraph(navController = navController)
    }
}