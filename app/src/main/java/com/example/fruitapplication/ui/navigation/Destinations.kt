package com.example.fruitapplication.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fruitapplication.R

enum class Destinations (@StringRes val title: Int, val icon: ImageVector) {

    Fruits(title = R.string.fruits , icon= Icons.Outlined.MenuBook),
    About(title = R.string.about, icon= Icons.Outlined.Info),
}