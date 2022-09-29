package com.mazer.exhbuy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mazer.exhbuy.data.model.EventData
import com.mazer.exhbuy.ui.theme.AppTypography

@ExperimentalMaterial3Api
@Composable
fun EventCard(
    event: EventData
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable { /*TODO*/ }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Text(
                    text = "${event.date_of_start} - ${event.date_of_end}",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = AppTypography.titleSmall
                )
                Text(
                    text = event.event_name,
                    style = AppTypography.titleMedium
                )
                Text(
                    text = event.event_location,
                    style = AppTypography.titleSmall
                )
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp)
                    .align(Alignment.CenterEnd),
            ) {
                /* TODO Add image of event */
            }
        }
    }
}