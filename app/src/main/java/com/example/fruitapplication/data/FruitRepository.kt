package com.example.fruitapplication.data

import android.util.Log
import com.example.fruitapplication.data.database.FruitDao
import com.example.fruitapplication.data.database.asDbFruit
import com.example.fruitapplication.data.database.asDomainFruit
import com.example.fruitapplication.data.database.asDomainFruits
import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.network.FruitApiService
import com.example.fruitapplication.network.asDomainObject
import com.example.fruitapplication.network.asDomainObjects
import com.example.fruitapplication.network.getFruitAsFlow
import com.example.fruitapplication.network.getFruitsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.SocketTimeoutException

/**
 * Repository interface for fruit-related data operations.
 */
interface FruitRepository {

    suspend fun insert(item: Fruit)
    suspend fun insertFruit(id: Int)
    fun getFruits(): Flow<List<Fruit>>
    fun getFruit(id: Int): Flow<Fruit>
    suspend fun refresh()
}

/**
 * Implementation of the [FruitRepository] that caches data fetched from the API into a local database.
 */
class CachingFruitRepository(
    private val fruitDao: FruitDao,
    private val fruitApiService: FruitApiService,
) : FruitRepository {

    /**
     * Inserts a fruit item into the local database.
     */
     override suspend fun insert(item: Fruit) {
         fruitDao.insert(item.asDbFruit())
     }

    /**
     * Fetches a specific fruit from the API by its ID and inserts it into the local database.
     */
    override suspend fun insertFruit(id: Int) {
        try {
            fruitApiService.getFruitAsFlow(id).collect() {
                insert(it.asDomainObject())
            }
        } catch (e: SocketTimeoutException) {
            Log.e("FruitRepository", "insertFruit: ${e.message}")
        } catch (e: retrofit2.HttpException) {
            Log.e("FruitRepository", "insertFruit: ${e.message}")
        }
    }

    /**
     * Retrieves a list of fruits from the local database as a Flow.
     */
    override fun getFruits(): Flow<List<Fruit>> {
        return fruitDao.getFruits().map {
            it.asDomainFruits()
        }
    }

    /**
     * Retrieves a specific fruit by ID from the local database as a Flow.
     */
    override fun getFruit(id: Int): Flow<Fruit> {
        return fruitDao.getFruit(id).map {
            it.asDomainFruit()
        }
    }

    /**
     * Refreshes the data by fetching all fruits from the API and inserting them into the local database.
     */
    override suspend fun refresh() {
        try {
            fruitApiService.getFruitsAsFlow().collect() {
                for (fruit in it.asDomainObjects()) {
                    insert(fruit)
                }
            }
        } catch (e: SocketTimeoutException) {
            Log.e("FruitRepository", "refresh: ${e.message}")
        }
    }
}