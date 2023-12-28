package com.example.fruitapplication.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface FruitApiService {

    @GET("all")
    suspend fun getAllFruits(): List<ApiFruit>

}

fun FruitApiService.getFruitsAsFlow(): Flow<List<ApiFruit>> = flow {
    try {
        emit(getAllFruits())
    }
    catch(e: Exception){
        Log.e("API", "getFruitsAsFlow: "+e.stackTraceToString(), )
    }
}
