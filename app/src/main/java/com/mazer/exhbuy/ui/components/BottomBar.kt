package com.mazer.exhbuy.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mazer.exhbuy.ui.navigation.NavigationRouts

@Composable
fun BottomBar(
    stateValue: MutableState<String>
) {
    val screens = listOf(
        NavigationRouts.HOME,
        NavigationRouts.CREATING,
        NavigationRouts.SALE,
    )

    var bottomBarSize by remember { mutableStateOf(70.dp) }
    bottomBarSize = if (stateValue.value.lowercase() !in listOf(
            "home", "creating", "sale"
        )
    )
        0.dp
    else
        70.dp

    NavigationBar(
        modifier = Modifier.height(bottomBarSize)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                stateValue = stateValue
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationRouts,
    stateValue: MutableState<String>
) {
    NavigationBarItem(
        icon = {
            Icon(
                imageVector = screen.icon!!,
                contentDescription = "Navigation Icon"
            )
        },
        selected = stateValue.value.contentEquals(screen.route),
        onClick = {
            stateValue.value = screen.route.uppercase()
        }
    )
}