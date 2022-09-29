package com.mazer.exhbuy.ui.screens.home

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mazer.exhbuy.data.model.EventData
import com.mazer.exhbuy.data.model.TicketType
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.ui.screens.home.components.CreateTickets
import com.mazer.exhbuy.ui.theme.AppTypography
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatingScreen() {
    val db = Firebase.firestore
    val dbCollection = db.collection("Events")
    val focusManager = LocalFocusManager.current

    val eventName = remember { mutableStateOf("") }

    val eventLocation = remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val startDay = calendar.get(Calendar.DAY_OF_MONTH)
    val startMonth = calendar.get(Calendar.MONTH)
    val startYear = calendar.get(Calendar.YEAR)
    val endDay = calendar.get(Calendar.DAY_OF_MONTH)
    val endMonth = calendar.get(Calendar.MONTH)
    val endYear = calendar.get(Calendar.YEAR)
    val startDate = remember { mutableStateOf("") }
    val endDate = remember { mutableStateOf("") }
    val startDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, y, m, d ->
            startDate.value = "$d.${m + 1}.$y"
        }, startYear, startMonth, startDay
    )
    val endDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, y, m, d ->
            endDate.value = "$d.${m + 1}.$y"
        }, endYear, endMonth, endDay
    )

    val ticketTypesList = remember { mutableStateListOf<TicketType>() }

    val isCreatingButtonValid by derivedStateOf {
        eventName.value.isNotBlank() && eventName.value.length >= 3
                && eventLocation.value.isNotBlank()
                && startDate.value.isNotBlank()
                && endDate.value.isNotBlank()
                && ticketTypesList.isNotEmpty()
    }

    val headlinesStyle = AppTypography.labelMedium
    val headlineColor = MaterialTheme.colorScheme.onSurface

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "General",
                style = headlinesStyle,
                color = headlineColor,
            )

            Surface(
                tonalElevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = eventName.value,
                        onValueChange = { eventName.value = it },
                        singleLine = true,
                        label = { Text(text = "Event name") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp)
                    )

                    OutlinedTextField(
                        value = eventLocation.value,
                        onValueChange = { eventLocation.value = it },
                        singleLine = true,
                        label = { Text(text = "Event location") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp)
                    ) {

                        OutlinedTextField(
                            value = startDate.value,
                            onValueChange = {},
                            enabled = false,
                            singleLine = true,
                            label = { Text(text = "Date of start") },
                            modifier = Modifier
                                .clickable { startDatePickerDialog.show() }
                                .weight(0.4f).padding(end = 8.dp)
                        )

                        OutlinedTextField(
                            value = endDate.value,
                            onValueChange = {},
                            enabled = false,
                            singleLine = true,
                            label = { Text(text = "Date of end") },
                            modifier = Modifier
                                .clickable { endDatePickerDialog.show() }
                                .weight(0.4f)
                        )

                    }
                }
            }

            Text(
                text = "Tickets",
                style = headlinesStyle,
                color = headlineColor,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            Surface(
                tonalElevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                /* TODO lazycolumn of max 5 ticket types */
                CreateTickets(ticketTypesList)

            }

            Text(
                text = "Images",
                style = headlinesStyle,
                color = headlineColor,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            Button(
                enabled = isCreatingButtonValid,
                onClick = {
                    dbCollection.add(
                        EventData(
                            event_name = eventName.value,
                            event_location = eventLocation.value,
                            date_of_start = startDate.value,
                            date_of_end = endDate.value,
                        )
                    )
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                TAG,
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                }
            ) {
                Text(
                    text = "Add event",
                )
            }
        }
    }
}