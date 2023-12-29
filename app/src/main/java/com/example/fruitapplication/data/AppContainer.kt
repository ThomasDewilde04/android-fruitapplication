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

interface AppContainer {
    val fruitRepository: FruitRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    private val networkCheck = NetworkConnectionInterceptor(context)
    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .build()

    private val baseUrl = "https://www.fruityvice.com/api/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: FruitApiService by lazy {
        retrofit.create(FruitApiService::class.java)
    }

    override val fruitRepository: FruitRepository by lazy {
        CachingFruitRepository(FruitDb.getDatabase(context = context).fruitDao(), retrofitService)
    }

}
