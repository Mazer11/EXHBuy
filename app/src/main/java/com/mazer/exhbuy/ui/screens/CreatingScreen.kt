package com.mazer.exhbuy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mazer.exhbuy.data.DatabaseReferences
import com.mazer.exhbuy.data.EventData
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.ui.theme.AppTypography

@ExperimentalMaterial3Api
@Composable
fun CreatingScreen() {
    val db = Firebase.database("https://exhbuy-default-rtdb.europe-west1.firebasedatabase.app/")
    val myRef = db.getReference(DatabaseReferences.EVENTS.reference)

    var eventName by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }
    var ticketsCount by remember { mutableStateOf("0") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("0") }

    val textFieldModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)

    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(all = 16.dp)
        ) {
            //Event Name
            MyTextField(
                hint = "Event name",
                onTextValueChange = {
                    eventName = it
                },
                modifier = textFieldModifier
            )
            //Event Location
            MyTextField(
                hint = "Event location",
                onTextValueChange = {
                    eventLocation = it
                },
                modifier = textFieldModifier
            )
            //Count of tickets
            MyTextField(
                hint = "Tickets count",
                isNumericInput = true,
                onTextValueChange = {
                    ticketsCount = it
                },
                modifier = textFieldModifier
            )
            //Date of start
            MyTextField(
                hint = "Date of beginning",
                isNumericInput = true,
                onTextValueChange = {
                    startDate = it
                },
                modifier = textFieldModifier
            )
            //Date of end
            MyTextField(
                hint = "Date of ending",
                isNumericInput = true,
                onTextValueChange = {
                    endDate = it
                },
                modifier = textFieldModifier
            )
            //Price of ticket
            MyTextField(
                hint = "Ticket price",
                isNumericInput = true,
                onTextValueChange = {
                    price = it
                },
                modifier = textFieldModifier
            )
            Button(
                onClick = {
                    myRef.child(eventName).setValue(
                        EventData(
                            eventName = eventName,
                            location = eventLocation,
                            ticketsCount = ticketsCount.toInt(),
                            startDate = startDate,
                            endDate = endDate,
                            price = price.toInt()
                        )
                    )
                }
            ) {
                Text(
                    text = "Add event",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = AppTypography.titleMedium,
                )
            }
        }
    }
}