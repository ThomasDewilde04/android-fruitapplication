package com.example.fruitapplication

import android.app.Application
import com.example.fruitapplication.data.AppContainer
import com.example.fruitapplication.data.DefaultAppContainer


/**
 * Application class serving as the entry point for the Fruit Application.
 */
class FruitsApplication: Application() {
    /**
     * Reference to the application's dependency container.
     */
    lateinit var container: AppContainer

    /**
     * Called when the application is starting. Initializes the application container.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}