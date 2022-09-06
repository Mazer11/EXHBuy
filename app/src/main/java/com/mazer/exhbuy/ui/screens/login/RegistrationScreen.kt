package com.mazer.exhbuy.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.mazer.exhbuy.viewmodels.HomeVM

@Composable
fun RegistrationScreen(
    vm: HomeVM,
    activity: ComponentActivity,
    navController: NavController
) {

    val registrationType = remember { mutableStateOf("NONE") }

    when (registrationType.value) {
        "NONE" -> ChooseRegistrationType(registrationType)
        "GOOGLE" -> RegistrationGoogleState(registrationType, vm)
        else -> RegistrationPhoneState(registrationType, vm, activity, navController)
    }

}