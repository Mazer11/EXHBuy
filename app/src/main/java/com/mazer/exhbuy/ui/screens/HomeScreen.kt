package com.mazer.exhbuy.ui.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mazer.exhbuy.data.DatabaseReferences
import com.mazer.exhbuy.ui.components.HomeChips
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.ui.theme.EXHBuyTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {
    val dataBase = Firebase.database
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
                Column {
                    MyTextField(
                        hint = "Search events...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                    HomeChips()
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

            }
        }
    }
}