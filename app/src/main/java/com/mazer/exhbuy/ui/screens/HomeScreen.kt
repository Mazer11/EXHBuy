package com.mazer.exhbuy.ui.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mazer.exhbuy.EXHBuyNav
import com.mazer.exhbuy.data.DatabaseReferences
import com.mazer.exhbuy.ui.components.HomeChips
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.ui.theme.EXHBuyTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavHostController) {
    val dataBase = Firebase.database
    val mAuth = FirebaseAuth.getInstance()
    val myRef = dataBase.getReference(DatabaseReferences.EVENTS.reference)
    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

        }

        override fun onCancelled(error: DatabaseError) {
            Log.w(TAG, "Failed to read value.", error.toException())
        }
    })

    EXHBuyTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = {
                        MyTextField(
                            hint = "Search events...",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp)
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(EXHBuyNav.ACCOUNT.route)
                            },
                        ) {
                            val accountIcon = if(mAuth.currentUser?.photoUrl != null)
                                rememberImagePainter(data = mAuth.currentUser?.photoUrl)
                            else
                                rememberVectorPainter(image = Icons.Default.Face)
                            Icon(
                                painter = accountIcon,
                                contentDescription = "User Icon"
                            )
                        }
                    }
                )
            }
        ) {
            HomeChips()
        }
    }
}