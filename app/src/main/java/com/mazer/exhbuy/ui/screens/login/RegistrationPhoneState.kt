package com.mazer.exhbuy.ui.screens.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.mazer.exhbuy.R
import com.mazer.exhbuy.viewmodels.LoginVM

@Composable
fun RegistrationPhoneState(
    vm: LoginVM,
    activity: ComponentActivity,
    navController: NavController
) {
    var otpVal by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val isUsernameValid by derivedStateOf {
        username.value.length in 4..20
    }

    val isPhoneValid by derivedStateOf {
        phoneNumber.length == 10
    }

    val isOtpValid by derivedStateOf {
        otpVal.length == 6
    }

    val showOtp = vm.isOtpSended.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text(text = stringResource(id = R.string.username)) },
            singleLine = true,
            isError = isUsernameValid.not(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Next) }
            ),
            trailingIcon = {
                if (username.value.isNotBlank())
                    IconButton(onClick = { username.value = "" }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Made field clear"
                        )
                    }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "+7",
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text(text = "Phone number") },
                isError = isPhoneValid.not(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                trailingIcon = {
                    if (phoneNumber.isNotBlank())
                        IconButton(onClick = { phoneNumber = "" }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Make field clear"
                            )
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        Button(
            enabled = isPhoneValid && isUsernameValid,
            onClick = {
                vm.sendPhone(phoneNumber, context, activity, navController)
            }
        ) {
            Text(
                text = "Send verification code"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (showOtp.value == true) {
            OutlinedTextField(
                value = otpVal,
                onValueChange = { otpVal = it },
                label = { Text(text = "Verification code") },
                isError = isOtpValid.not(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(16.dp))

            Button(
                enabled = isOtpValid,
                onClick = {
                    if (otpVal != "")
                        vm.verifyOtp(otpVal, username, context, navController)
                }
            ) {
                Text(
                    text = "Verify"
                )
            }
        }
    }
}