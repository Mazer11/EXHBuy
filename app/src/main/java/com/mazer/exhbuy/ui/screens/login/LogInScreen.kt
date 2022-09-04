package com.mazer.exhbuy.ui.screens.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.viewmodels.RegistrationVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavController,
    vm: RegistrationVM
) {
    Log.d("StartApp", "In LogInScreen")
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

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