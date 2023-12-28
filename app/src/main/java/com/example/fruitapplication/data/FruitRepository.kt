package com.example.fruitapplication.data

import android.util.Log
import androidx.work.WorkInfo
import com.example.fruitapplication.data.database.FruitDao
import com.example.fruitapplication.data.database.asDbFruit
import com.example.fruitapplication.data.database.asDomainFruits
import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.network.FruitApiService
import com.example.fruitapplication.network.asDomainObjects
import com.example.fruitapplication.network.getFruitsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.SocketTimeoutException

interface FruitRepository {

    suspend fun insert(item: Fruit)
    fun getFruits(): Flow<List<Fruit>>
    suspend fun refresh()
}

class CachingFruitRepository(
    private val fruitDao: FruitDao,
    private val fruitApiService: FruitApiService,
) : FruitRepository {

     override suspend fun insert(item: Fruit) {
         fruitDao.insert(item.asDbFruit())
     }

    override fun getFruits(): Flow<List<Fruit>> {
        return fruitDao.getFruits().map {
            it.asDomainFruits()
        }
    }

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