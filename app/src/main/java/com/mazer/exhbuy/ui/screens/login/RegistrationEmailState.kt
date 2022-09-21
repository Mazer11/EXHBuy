package com.mazer.exhbuy.ui.screens.login

import android.net.Uri
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.mazer.exhbuy.R
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.viewmodels.LoginVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationEmailState(
    vm: LoginVM,
    navController: NavController
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val isPasswordVisible = remember { mutableStateOf(false) }
    val isConfirmPasswordVisible = remember { mutableStateOf(false) }
    val isRegistrationEnabled = remember { mutableStateOf(false) }
    val TAG = "registration_email"

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    val isUsernameValid by derivedStateOf {
        username.value.length in 4..20
    }

    val isPasswordValid by derivedStateOf {
        password.value.length in 8..20
    }

    val isConfirmPasswordValid by derivedStateOf {
        confirmPassword.value == password.value
                && confirmPassword.value.isNotBlank()
                && password.value.isNotBlank()
    }

    isRegistrationEnabled.value =
        isEmailValid && isPasswordValid && isConfirmPasswordValid && isUsernameValid

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
                isError = isEmailValid.not(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(focusDirection = FocusDirection.Next) }
                ),
                trailingIcon = {
                    if (email.value.isNotBlank())
                        IconButton(onClick = { email.value = "" }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Made field clear"
                            )
                        }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

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
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))


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
                trailingIcon = {
                    IconButton(onClick = {
                        isPasswordVisible.value = isPasswordVisible.value.not()
                    }) {
                        Icon(
                            imageVector = if (isPasswordVisible.value)
                                Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility,
                            contentDescription = "Show password"
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible.value)
                    VisualTransformation.None
                else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

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
                trailingIcon = {
                    IconButton(onClick = {
                        isConfirmPasswordVisible.value = isConfirmPasswordVisible.value.not()
                    }) {
                        Icon(
                            imageVector = if (isConfirmPasswordVisible.value)
                                Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility,
                            contentDescription = "Show password"
                        )
                    }
                },
                visualTransformation = if (isConfirmPasswordVisible.value)
                    VisualTransformation.None
                else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    vm.mAuth.createUserWithEmailAndPassword(email.value, password.value)
                        .addOnCompleteListener {
                            val updateRequest = userProfileChangeRequest {
                                displayName = username.value
                                //photoUri = Uri.parse("")
                            }

                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Registration was successfully done",
                                    Toast.LENGTH_SHORT
                                ).show()
                                vm.mAuth.currentUser!!.sendEmailVerification()
                                vm.mAuth.currentUser!!.updateProfile(updateRequest)
                                    .addOnCompleteListener { task ->
                                        if(task.isSuccessful)
                                            Log.d(TAG, "User profile updated.")
                                    }
                                navController.navigate(NavigationRouts.HOME.route)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Registration was failed. Try again later",
                                    Toast.LENGTH_SHORT
                                )
                            }
                        }
                },
                enabled = isRegistrationEnabled.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

                Text(text = stringResource(R.string.confirm))

            }
        }
    }
}