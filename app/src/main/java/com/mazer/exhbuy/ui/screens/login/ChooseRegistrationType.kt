package com.mazer.exhbuy.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChooseRegistrationType(
    currentState: MutableState<String>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = {
                    currentState.value = "GOOGLE"
                }
            ) {
                Text(
                    text = "Authenticate with Google"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    currentState.value = "PHONE"
                }
            ) {
                Text(
                    text = "Authenticate with phone"
                )
            }

        }
    }
}