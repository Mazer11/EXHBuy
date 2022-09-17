package com.mazer.exhbuy.ui.screens.login

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mazer.exhbuy.R
import com.mazer.exhbuy.viewmodels.LoginVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationEmailState(
    vm: LoginVM
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val isRegistrationEnabled = remember { mutableStateOf(false) }

    val isLoginValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.value.length > 8
        //other rules
    }

    val isConfirmPasswordValid by derivedStateOf {
        confirmPassword.value == password.value
    }

    isRegistrationEnabled.value = isLoginValid && isPasswordValid

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) { padding ->

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = stringResource(id = R.string.email_address)) },
                singleLine = true,
                isError = isLoginValid.not(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Next) }
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(text = stringResource(id = R.string.password)) },
                singleLine = true,
                isError = isPasswordValid.not(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                label = { Text(text = stringResource(R.string.confirm_password)) },
                singleLine = true,
                isError = isConfirmPasswordValid.not(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { /*TODO*/ },
                enabled = isLoginValid && isConfirmPasswordValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

                Text(text = stringResource(R.string.confirm))

            }
        }
    }
}