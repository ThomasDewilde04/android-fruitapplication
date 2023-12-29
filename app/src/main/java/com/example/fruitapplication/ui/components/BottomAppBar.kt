package com.example.fruitapplication.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Composable representing the bottom app bar with navigation functionality, displaying items with icons and labels.
 *
 * @param items A list of string items representing the labels for the navigation items.
 * @param icons A list of ImageVector icons corresponding to the navigation items.
 * @param selectedItem The index of the currently selected item in the bottom app bar.
 * @param onItemSelected A callback function invoked when an item in the bottom app bar is selected.
 */
@Composable
fun BottomAppBar(
    items: List<String>,
    icons: List<ImageVector>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
) {
    NavigationBar(
        containerColor = Color(0xFF71C543),
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        // Iterate through the provided items to create the navigation bar items
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = item,
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.DarkGray,
                    selectedTextColor = Color.DarkGray,
                    indicatorColor = MaterialTheme.colorScheme.errorContainer,
                ),
            )
        }
    }
}