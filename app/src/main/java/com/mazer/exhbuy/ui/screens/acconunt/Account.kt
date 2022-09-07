package com.mazer.exhbuy.ui.screens.acconunt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.ui.navigation.NavigationRouts

@Composable
fun AccountScreen(
    navController: NavController
){
    val mAuth = FirebaseAuth.getInstance()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            mAuth.signOut()
            val startRoute =navController.graph.startDestinationId
            navController.popBackStack(destinationId = startRoute, inclusive = true)
            navController.navigate(NavigationRouts.LOGIN.route){
            }
        }) {
            Text(text = "Sign Out")
        }
    }
}