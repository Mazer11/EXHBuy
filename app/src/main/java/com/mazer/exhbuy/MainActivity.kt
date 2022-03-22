package com.mazer.exhbuy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.ui.screens.SignInActivity
import com.mazer.exhbuy.ui.theme.EXHBuyTheme

class MainActivity : ComponentActivity() {
    var mAuth = FirebaseAuth.getInstance()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth.addAuthStateListener {
            if (mAuth.currentUser == null) {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        setContent {
            EXHBuyTheme {
                EXHBuyApp()
            }
        }
    }
}
