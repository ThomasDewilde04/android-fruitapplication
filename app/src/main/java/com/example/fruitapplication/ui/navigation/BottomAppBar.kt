package com.example.fruitapplication.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

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