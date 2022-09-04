package com.mazer.exhbuy.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.ui.theme.AppTypography
import com.mazer.exhbuy.viewmodels.RegistrationVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationPhoneState(
    currentState: MutableState<String>,
    vm: RegistrationVM,
    activity: ComponentActivity,
    navController: NavController
) {
    var otpVal = ""
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = "+7",
                            style = AppTypography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    MyTextField(
                        hint = "Enter phone number...",
                        isNumericInput = true,
                        onTextValueChange = {
                            phoneNumber = it
                        }
                    )
                }
                Button(
                    onClick = {
                        if (phoneNumber.isNotEmpty())
                            vm.sendPhone(phoneNumber, context, activity, navController)
                    }
                ) {
                    Text(
                        text = "Get code"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                MyTextField(
                    hint = "Enter verification code...",
                    isNumericInput = true,
                    onTextValueChange = {
                        otpVal = it
                    }
                )
                Button(
                    onClick = {
                        if (otpVal != "")
                            vm.verifyOtp(otpVal, context, navController)
                    }
                ) {
                    Text(
                        text = "Verify"
                    )
                }
            }
        }
    }
}