package com.mazer.exhbuy.ui.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.viewmodels.LoginVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavController,
    vm: LoginVM
) {
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(250.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email Address") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Password") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Button(
                            onClick = {
                                /*TODO*/
                            }
                        ) {
                            Text(text = "Log In")
                        }
                    }
                }
                Text(
                    text = "Registration",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(NavigationRouts.REGISTRATION.route)
                        }
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp)
                )
//            }
        }
    }
}