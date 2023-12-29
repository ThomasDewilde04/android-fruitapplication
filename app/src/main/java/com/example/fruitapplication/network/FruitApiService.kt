package com.example.fruitapplication.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service interface defining API endpoints to retrieve fruit data.
 */
interface FruitApiService {

    /**
     * Retrieves all fruits available from the API.
     *
     * @return List<ApiFruit> A list of all fruits obtained from the API.
     */
    @GET("fruit/all")
    suspend fun getAllFruits(): List<ApiFruit>

    /**
     * Retrieves a specific fruit based on the provided ID.
     *
     * @param id The unique identifier of the fruit.
     * @return ApiFruit The fruit object obtained based on the ID.
     */
    @GET("fruit/{id}")
    suspend fun getFruit(@Path("id") id: Int): ApiFruit
}

/**
 * Extension function to convert the retrieval of all fruits into a Flow.
 *
 * @return Flow<List<ApiFruit>> A Flow emitting a list of all fruits obtained from the API.
 */
fun FruitApiService.getFruitsAsFlow(): Flow<List<ApiFruit>> = flow {
    try {
        emit(getAllFruits())
    }
    catch(e: Exception){
        Log.e("API", "getFruitsAsFlow: "+e.stackTraceToString(), )
    }
}

/**
 * Extension function to convert the retrieval of a single fruit into a Flow.
 *
 * @param id The unique identifier of the fruit.
 * @return Flow<ApiFruit> A Flow emitting the fruit object obtained based on the provided ID.
 */
fun FruitApiService.getFruitAsFlow(id: Int) = flow {
    try {
        emit(getFruit(id))
    }
    catch(e: Exception){
        Log.e("API", "getFruitAsFlow: "+e.stackTraceToString(), )
    }
}
