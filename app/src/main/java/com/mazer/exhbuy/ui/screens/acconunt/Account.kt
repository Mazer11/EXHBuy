package com.mazer.exhbuy.ui.screens.acconunt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController,
    mAuth: FirebaseAuth
) {
    val user = mAuth.currentUser

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                if (user != null) {
                    if (user.photoUrl != null)
                        Image(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = CircleShape
                                ),
                            painter = rememberAsyncImagePainter(model = user.photoUrl),
                            contentDescription = "User avatar image",
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop
                        )
                    else
                        Text(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = CircleShape
                                ),
                            text = user.displayName!!.first().uppercase(),
                            textAlign = TextAlign.Center,
                            style = AppTypography.displayLarge
                        )

                    Text(
                        text = user.displayName!!,
                        style = AppTypography.headlineMedium
                    )
                }
            }

            Button(
                onClick = {
                    mAuth.signOut()
                    val startRoute = navController.graph.startDestinationId
                    navController.popBackStack(destinationId = startRoute, inclusive = true)
                    navController.navigate(NavigationRouts.HOME.route)
                }
            ) {
                Text(text = "Sign Out")
            }
        }
    }
}