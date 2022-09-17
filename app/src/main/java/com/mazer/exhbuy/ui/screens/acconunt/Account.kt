package com.mazer.exhbuy.ui.screens.acconunt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.ui.navigation.NavigationRouts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController,
    mAuth: FirebaseAuth
) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = {
                mAuth.signOut()
                val startRoute = navController.graph.startDestinationId
                navController.popBackStack(destinationId = startRoute, inclusive = true)
                navController.navigate(NavigationRouts.HOME.route)
            }) {
                Text(text = "Sign Out")
            }
        }

    }
}