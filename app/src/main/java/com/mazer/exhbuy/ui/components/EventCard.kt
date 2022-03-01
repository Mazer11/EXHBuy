package com.mazer.exhbuy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mazer.exhbuy.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventCard() {
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
                    text = "Date and time",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = AppTypography.titleSmall
                )
                Text(
                    text = "Title",
                    style = AppTypography.titleMedium
                )
                Text(
                    text = "Place",
                    style = AppTypography.titleSmall
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "1000 - 1000 Price",
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

@Preview(
    name = "Light Theme"
)
@Composable
fun EventCardPreview() {
    EventCard()
}


























