package com.mazer.exhbuy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mazer.exhbuy.EXHBuyNav
import com.mazer.exhbuy.ui.components.MyTextField

@Composable
fun LogInScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
                    navController.navigate(EXHBuyNav.SIGNINCHOOSE.route)
                }
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}