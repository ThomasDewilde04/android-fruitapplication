package com.example.fruitapplication

import android.app.Application
import com.example.fruitapplication.data.AppContainer
import com.example.fruitapplication.data.DefaultAppContainer

class FruitsApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}