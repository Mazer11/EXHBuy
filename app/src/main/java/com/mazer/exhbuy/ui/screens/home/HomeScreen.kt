package com.mazer.exhbuy.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.data.model.EventData
import com.mazer.exhbuy.data.db.FirestoreDao
import com.mazer.exhbuy.ui.components.EventCard
import com.mazer.exhbuy.ui.components.HomeChips
import com.mazer.exhbuy.ui.components.MyTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val mAuth = FirebaseAuth.getInstance()
    val eventList = remember { mutableStateListOf(EventData()) }

    FirestoreDao.getEvents(eventList)

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
                            navController.navigate(NavigationRouts.ACCOUNT.route)
                        },
                    ) {
                        val accountIcon = if (mAuth.currentUser?.photoUrl != null)
                            rememberAsyncImagePainter(model = mAuth.currentUser?.photoUrl)
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
        Column(
            modifier = Modifier.padding(it)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            ) {
                HomeChips()
            }
            Box(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(eventList) { item: EventData ->
                        EventCard(event = item)
                    }
                }
            }
        }
    }
}






















