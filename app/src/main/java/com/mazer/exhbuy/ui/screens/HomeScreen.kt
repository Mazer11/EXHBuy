package com.mazer.exhbuy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mazer.exhbuy.data.Chip
import com.mazer.exhbuy.ui.components.EventCard
import com.mazer.exhbuy.ui.components.HomeChips
import com.mazer.exhbuy.ui.components.SearchBar
import com.mazer.exhbuy.ui.theme.EXHBuyTheme

@Composable
fun HomeScreen() {
    EXHBuyTheme {
        Column {
            SearchBar(
                hint = "Search events...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
            HomeChips()
            EventCard()
            EventCard()
            EventCard()
        }
    }
}