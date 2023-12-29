package com.example.fruitapplication.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fruitapplication.R

/**
 * Enum class representing different destinations/screens in the Fruit Application.
 *
 * @param title The title of the destination, typically represented as a string resource.
 * @param icon The icon associated with the destination, usually an image vector.
 */
enum class Destinations (@StringRes val title: Int, val icon: ImageVector) {

    /**
     * Represents the 'Fruits' screen.
     */
    Fruits(title = R.string.fruits , icon= Icons.Outlined.MenuBook),

    /**
     * Represents the 'About' screen.
     */
    About(title = R.string.about, icon= Icons.Outlined.Info),
}