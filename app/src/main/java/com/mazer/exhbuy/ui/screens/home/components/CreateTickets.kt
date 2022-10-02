package com.mazer.exhbuy.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mazer.exhbuy.data.model.TicketType

@Composable
fun CreateTickets(
    ticketsList: MutableList<TicketType>
) {
    val focusManager = LocalFocusManager.current

    val ticketTypeName = remember { mutableStateOf("") }
    val ticketTypeCount = remember { mutableStateOf(0) }
    val ticketTypePrice = remember { mutableStateOf(0.0f) }
    val isEnabled = remember { mutableStateOf(true) }
    val isTicketTypeDataValid by derivedStateOf {
        ticketTypeName.value.isNotBlank()
                && ticketTypeName.value.length >= 4
                && ticketTypeCount.value in 1..1000
                && ticketTypePrice.value > 0.0f
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 8.dp)) {

        OutlinedTextField(
            value = ticketTypeName.value,
            onValueChange = { ticketTypeName.value = it },
            label = { Text(text = "Ticket type name") },
            singleLine = true,
            enabled = isEnabled.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {

            OutlinedTextField(
                value = ticketTypePrice.value.toString(),
                onValueChange = { ticketTypePrice.value = it.toFloat() },
                label = { Text(text = "Price") },
                singleLine = true,
                enabled = isEnabled.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Right) }
                ),
                modifier = Modifier
                    .weight(0.3f)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = ticketTypeCount.value.toString(),
                onValueChange = { ticketTypeCount.value = it.toInt() },
                label = { Text(text = "Count") },
                singleLine = true,
                enabled = isEnabled.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                modifier = Modifier.weight(0.3f)
            )
        }

        if (isEnabled.value)
            Button(
                enabled = isTicketTypeDataValid,
                onClick = {
                    val ticketType = TicketType(
                        ticket_type_name = ticketTypeName.value,
                        ticket_type_count = ticketTypeCount.value,
                        ticket_type_price = ticketTypePrice.value
                    )
                    if (ticketsList.contains(ticketType).not()) {
                        ticketsList.add(ticketType)
                        ticketTypeName.value = ""
                        ticketTypeCount.value = 0
                        ticketTypePrice.value = 0.0f
                    }
                }
            ) {
                Text(text = "Add")
            }
    }
}
































