package com.mazer.exhbuy.ui.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.mazer.exhbuy.viewmodels.LoginVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    vm: LoginVM,
    activity: ComponentActivity,
    navController: NavController
) {

    val registrationType = remember { mutableStateOf("NONE") }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        when (registrationType.value) {
            "NONE" -> ChooseRegistrationType(registrationType)
            "EMAIL" -> RegistrationEmailState(vm, navController)
            else -> RegistrationPhoneState(vm, activity, navController)
        }
    }
}