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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import com.mazer.exhbuy.data.model.EventData
import com.mazer.exhbuy.data.db.FirestoreDao
import com.mazer.exhbuy.ui.components.EventCard
import com.mazer.exhbuy.ui.components.HomeChips
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.ui.screens.login.LogInScreen
import com.mazer.exhbuy.viewmodels.HomeVM

@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeVM,
) {
    val mAuth = FirebaseAuth.getInstance()

    if (mAuth.currentUser != null) {
        HomeList(navController = navController, accountInfo = mAuth.currentUser)
    }
    else {
        LogInScreen(navController = navController, vm = vm)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeList(
    navController: NavController,
    accountInfo: FirebaseUser?
) {
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
                        val accountIcon = if (accountInfo?.photoUrl != null)
                            rememberAsyncImagePainter(model = accountInfo.photoUrl)
                        else
                            rememberVectorPainter(image = Icons.Default.Face)
                        Icon(
                            painter = accountIcon,
                            contentDescription = "User Icon"
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(it).fillMaxSize()
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
                    .fillMaxSize()
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





















