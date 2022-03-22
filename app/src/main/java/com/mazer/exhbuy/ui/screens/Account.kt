package com.mazer.exhbuy.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AccountScreen(){
    val mAuth = FirebaseAuth.getInstance()

    Button(onClick = {
        mAuth.signOut()
    }) {
        Text(text = "Sign Out")
    }
}