package com.example.fruitapplication.ui.navigation

import androidx.annotation.StringRes
import com.example.fruitapplication.R

enum class Destinations (@StringRes val title: Int) {
    Start(title = R.string.fruits),
    Detail(title = R.string.detail),
}