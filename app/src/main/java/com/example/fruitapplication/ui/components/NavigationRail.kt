package com.example.fruitapplication.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.example.fruitapplication.ui.navigation.Destinations

/**
 * Composable representing the navigation rail with navigation functionality, displaying items with icons.
 *
 * @param selectedDestination The currently selected destination.
 * @param onTabPressed The callback function invoked when a tab is pressed.
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
fun FruitNavigationRail(selectedDestination: NavDestination?, onTabPressed: (String) -> Unit, modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier
    ) {
        for (navItem in Destinations.values()) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick = { onTabPressed(navItem.name) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                    )
                },
            )
        }
    }
}