package com.mazer.exhbuy.ui.screens.exdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mazer.exhbuy.data.db.FirestoreDao
import com.mazer.exhbuy.data.model.TicketType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExhibitionScreen(
    navController: NavController,
    event_id: String
) {
    val tickets = remember { mutableStateListOf<TicketType>() }
    FirestoreDao.getTicketTypesForEvent(event = event_id, tickets = tickets)

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            tickets.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp)
                ) {
                    Text(text = it.ticket_type_name)
                }
            }
        }

    }

}