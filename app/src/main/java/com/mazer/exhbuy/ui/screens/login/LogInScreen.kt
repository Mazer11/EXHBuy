package com.mazer.exhbuy.ui.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.viewmodels.LoginVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavController,
    vm: LoginVM
) {
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val mAuth = FirebaseAuth.getInstance()


    if (mAuth.currentUser != null) {
        if (vm.isLoadingState.value == true)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp
                )
            }
        else navController.navigate(NavigationRouts.HOME.route)
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = "Skip",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 8.dp, top = 8.dp)
                        .clickable {
                            navController.navigate(NavigationRouts.HOME.route)
                        }
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(250.dp))
                    MyTextField(
                        hint = "Phone number",
                        onTextValueChange = { phoneNumber = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MyTextField(
                        hint = "Password",
                        onTextValueChange = { password = it }
                    )
                    Button(
                        modifier = Modifier.padding(vertical = 8.dp),
                        onClick = {
                            /*TODO*/
                        }
                    ) {
                        Text(text = "Log In")
                    }
                    Button(
                        modifier = Modifier.padding(vertical = 16.dp),
                        onClick = {
                            /*TODO*/
                        }
                    ) {
                        Text(text = "Log In with Google")
                    }
                }
                Text(
                    text = "Registration",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(NavigationRouts.REGISTRATION.route)
                        }
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}