package com.mazer.exhbuy.ui.screens.acconunt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AccountScreen(){
    val mAuth = FirebaseAuth.getInstance()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            mAuth.signOut()
        }) {
            Text(text = "Sign Out")
        }
    }
}