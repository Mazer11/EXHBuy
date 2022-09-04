package com.mazer.exhbuy.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mazer.exhbuy.ui.theme.AppTypography

@Composable
fun HomeChips() {
    //temp
    val labels = listOf(
        "Label 1",
        "Label 2",
        "Label 3",
        "Label 4",
        "Label 5",
        "Label 6",
        "Label 7",
        "Label 8",
    )
    var labelIndex by remember { mutableStateOf(0) }

    LazyRow(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        item {
            labels.forEachIndexed { index, title ->
                FilterChip(
                    label = title,
                    clicked = index == labelIndex,
                    onChipClick = {
                        labelIndex = index
                    }
                )
            }
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    clicked: Boolean,
    onChipClick: () -> Unit
) {
    val color: Color
    val borderColor: Color
    val contentColor: Color

    if (clicked) {
        color = MaterialTheme.colorScheme.secondaryContainer
        borderColor = Color.Transparent
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        color = MaterialTheme.colorScheme.surface
        borderColor = MaterialTheme.colorScheme.outline
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    }


    Surface(
        shape = RoundedCornerShape(8.dp),
        color = color,
        tonalElevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .height(32.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                onChipClick()
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = label,
                    color = contentColor,
                    style = AppTypography.labelLarge
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}









































