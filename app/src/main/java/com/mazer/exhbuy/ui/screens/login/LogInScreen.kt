package com.mazer.exhbuy.ui.screens.login

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.R
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.viewmodels.LoginVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavController,
    vm: LoginVM,
    auth: FirebaseAuth,
    activity: androidx.core.app.ComponentActivity
) {
    val loginType = remember { mutableStateOf("EMAIL") }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 128.dp)
                    .padding(vertical = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = { loginType.value = "EMAIL" },
                        modifier = Modifier
                            .width(100.dp)
                            .background(
                                color = if (loginType.value == "EMAIL") MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Text(text = stringResource(id = R.string.email))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    TextButton(
                        onClick = { loginType.value = "PHONE" },
                        modifier = Modifier
                            .width(100.dp)
                            .background(
                                color = if (loginType.value == "PHONE") MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Text(text = stringResource(id = R.string.phone))
                    }
                }

                Divider(
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 8.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.Center)
            ) {
                when (loginType.value) {
                    "EMAIL" -> EmailLoginForm(auth = auth, navController = navController)
                    "PHONE" -> PhoneLoginForm(
                        vm = vm,
                        activity = activity,
                        navController = navController
                    )
                }

                Button(
                    onClick = {
                        navController.navigate(NavigationRouts.REGISTRATION.route)
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.registration))
                }
            }
            Text(text = stringResource(R.string.forgot_password), modifier = Modifier
                .clickable {
                    /*TODO*/
                }
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp))
        }
    }
}

@Composable
fun EmailLoginForm(
    auth: FirebaseAuth,
    navController: NavController
) {
    val context = LocalContext.current
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val isPasswordVisible = remember { mutableStateOf(false) }

    val isLoginValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.value.length in 8..20
    }

    val isLoginEnabled by derivedStateOf {
        isLoginValid && isPasswordValid
    }

    OutlinedTextField(
        value = email.value,
        onValueChange = { email.value = it },
        label = { Text(text = stringResource(R.string.email_address)) },
        singleLine = true,
        isError = isLoginValid.not(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        trailingIcon = {
            if (email.value.isNotBlank())
                IconButton(onClick = { email.value = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Make field clear"
                    )
                }
        },
        modifier = Modifier.fillMaxWidth()
    )

    OutlinedTextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text(text = stringResource(R.string.password)) },
        isError = isPasswordValid.not(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    )

    Button(
        enabled = isLoginEnabled,
        onClick = {
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Logged in successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(NavigationRouts.HOME.route)
                    } else
                        Toast.makeText(
                            context,
                            "Failed to log in",
                            Toast.LENGTH_SHORT
                        ).show()
                }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 32.dp)
    ) {
        Text(text = stringResource(R.string.login))
    }
}

@Composable
fun PhoneLoginForm(
    vm: LoginVM,
    activity: ComponentActivity,
    navController: NavController
) {
    var otpVal by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val isPhoneValid by derivedStateOf {
        phoneNumber.length == 10 && phoneNumber.startsWith("9")
    }

    val isOtpValid by derivedStateOf {
        otpVal.length == 6
    }

    val showOtp = vm.isOtpSended.observeAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
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
                .padding(horizontal = 16.dp, vertical = 17.dp)
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

    Button(
        enabled = isPhoneValid,
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

        Button(
            enabled = isOtpValid,
            onClick = {
                if (otpVal != "")
                    vm.verifyOtp(otp = otpVal, context = context, navController =  navController)
            }
        ) {
            Text(
                text = "Verify"
            )
        }
    }
}