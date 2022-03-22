package com.mazer.exhbuy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mazer.exhbuy.data.EventData
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
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp)
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = "${event.startDate} - ${event.endDate}",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = AppTypography.titleSmall
                )
                Text(
                    text = event.eventName,
                    style = AppTypography.titleMedium
                )
                Text(
                    text = event.location,
                    style = AppTypography.titleSmall
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${event.price} $",
                    style = AppTypography.titleMedium
                )
            }
            Surface(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .width(70.dp),
            ) {
                /*TODO Image*/
            }
        }
    }
}