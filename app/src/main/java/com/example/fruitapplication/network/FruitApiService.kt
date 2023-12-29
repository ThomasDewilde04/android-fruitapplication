package com.example.fruitapplication.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path

interface FruitApiService {

    @GET("fruit/all")
    suspend fun getAllFruits(): List<ApiFruit>

    @GET("fruit/{id}")
    suspend fun getFruit(@Path("id") id: Int): ApiFruit
}

fun FruitApiService.getFruitsAsFlow(): Flow<List<ApiFruit>> = flow {
    try {
        emit(getAllFruits())
    }
    catch(e: Exception){
        Log.e("API", "getFruitsAsFlow: "+e.stackTraceToString(), )
    }
}

fun FruitApiService.getFruitAsFlow(id: Int) = flow {
    try {
        emit(getFruit(id))
    }
    catch(e: Exception){
        Log.e("API", "getFruitAsFlow: "+e.stackTraceToString(), )
    }
}
