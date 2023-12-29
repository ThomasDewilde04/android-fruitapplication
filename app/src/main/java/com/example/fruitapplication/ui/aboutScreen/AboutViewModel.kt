package com.example.fruitapplication.ui.aboutScreen

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * About view model
 *
 * @constructor Create About view model
 */
class AboutViewModel : ViewModel() {
    init {
        Log.i("vm inspection", "AboutViewModel init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("vm inspection", "AboutViewModel cleared")
    }
}