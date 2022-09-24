package com.mazer.exhbuy.ui.screens.acconunt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.R
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController,
    mAuth: FirebaseAuth
) {
    val user = mAuth.currentUser
    val headlinesStyle = AppTypography.labelMedium
    val headlineColor = MaterialTheme.colorScheme.onSurface
    val textButtonColor = MaterialTheme.colorScheme.onSurfaceVariant

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            ) {
                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    if (user != null) {
                        if (user.photoUrl != null)
                            Image(
                                modifier = Modifier
                                    .size(64.dp)
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
                                text = user.displayName!!.substring(range = 0..1).uppercase(),
                                textAlign = TextAlign.Center,
                                style = AppTypography.headlineMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                        shape = CircleShape
                                    )
                                    .padding(all = 8.dp)
                            )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = user.displayName!!,
                            style = AppTypography.headlineMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }

            Text(
                text = stringResource(id = R.string.account_management),
                style = headlinesStyle,
                color = headlineColor,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Edit account info",
                    color = textButtonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable { /*TODO*/ }
                )

                Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.surface)

                Text(
                    text = "Upgrade to curator account",
                    color = textButtonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable { /*TODO*/ }
                )

                Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.surface)

                Text(
                    text = "See favorites",
                    color = textButtonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable { /*TODO*/ }
                )

                Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.surface)

                Text(
                    text = "Tickets history",
                    color = textButtonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable { /*TODO*/ }
                )

                Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.surface)

                Text(
                    text = "Sign out",
                    color = textButtonColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable {
                            mAuth.signOut()
                            val startRoute = navController.graph.startDestinationId
                            navController.popBackStack(destinationId = startRoute, inclusive = true)
                            navController.navigate(NavigationRouts.HOME.route)
                        }
                )
            }

            Text(
                text = "Application settings",
                style = headlinesStyle,
                color = headlineColor,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Settings",
                    color = textButtonColor,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable { navController.navigate(NavigationRouts.SETTINGS.route) }
                )
            }

            Text(
                text = "Application info",
                style = headlinesStyle,
                color = headlineColor,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                Text(
                    text = "FAQ",
                    color = textButtonColor,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable { /*TODO*/ }
                )

                Divider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.surface)

                Text(
                    text = "About us",
                    color = textButtonColor,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                        .clickable { /*TODO*/ }
                )
            }
        }
    }
}