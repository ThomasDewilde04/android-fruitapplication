package com.example.fruitapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.fruitapplication.ui.FruitApp
import com.example.fruitapplication.ui.theme.FruitApplicationTheme
import com.example.fruitapplication.ui.util.FruitNavigationType

/**
 * Main activity responsible for initializing and setting up the Fruit Application.
 */
class MainActivity : ComponentActivity() {

    /**
     * Initializes the activity and sets up the Fruit Application based on window size.
     *
     * @param savedInstanceState The saved instance state.
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("vm inspection", "Main activity onCreate")

        setContent {
            FruitApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            FruitApp(FruitNavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            FruitApp(FruitNavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            FruitApp(navigationType = FruitNavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                        else -> {
                            FruitApp(navigationType = FruitNavigationType.BOTTOM_NAVIGATION)
                        }
                    }
                }
            }
        }

    }
}

