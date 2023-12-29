package com.example.fruitapplication.data

import android.content.Context
import com.example.fruitapplication.data.database.FruitDb
import com.example.fruitapplication.network.FruitApiService
import com.example.fruitapplication.network.NetworkConnectionInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Interface defining a container for app-level dependencies.
 */
interface AppContainer {
    val fruitRepository: FruitRepository
}

/**
 * Default implementation of [AppContainer] providing dependencies for the app's data layer.
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    // Network connection interceptor for checking internet connectivity
    private val networkCheck = NetworkConnectionInterceptor(context)

    // OkHttpClient for making network requests
    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .build()

    // Base URL for the API service
    private val baseUrl = "https://www.fruityvice.com/api/"

    // Retrofit instance for handling API calls
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    // Retrofit service interface for Fruit API
    private val retrofitService: FruitApiService by lazy {
        retrofit.create(FruitApiService::class.java)
    }

    // Lazy initialization of the FruitRepository using CachingFruitRepository
    override val fruitRepository: FruitRepository by lazy {
        CachingFruitRepository(FruitDb.getDatabase(context = context).fruitDao(), retrofitService)
    }

}
