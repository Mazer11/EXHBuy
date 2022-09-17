package com.mazer.exhbuy.ui.screens.login

import android.util.Patterns
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mazer.exhbuy.R
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
    val isLoginEnabled = remember{ mutableStateOf(false) }

    val isLoginValid by derivedStateOf{
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.length > 8
        //other rules
    }

    // val showPassword = remember { mutableStateOf(false) }

    isLoginEnabled.value = isLoginValid && isPasswordValid

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
                    .align(Alignment.Center)
            ) {

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = stringResource(R.string.email_address)) },
                    singleLine = true,
                    isError = isLoginValid.not(),
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
                    label = { Text(text = stringResource(R.string.password)) },
                    isError = isPasswordValid.not(),
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

                Button(
                    enabled = isLoginEnabled.value,
                    onClick = {
                        /*TODO*/
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 8.dp)
                ) {
                    Text(text = stringResource(R.string.login))
                }

                Button(
                    onClick = {
                        navController.navigate(NavigationRouts.REGISTRATION.route)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.registration))
                }
            }
            Text(
                text = stringResource(R.string.forgot_password),
                modifier = Modifier
                    .clickable {
                        /*TODO*/
                    }
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            )
        }
    }
}