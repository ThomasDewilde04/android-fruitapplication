package com.example.fruitapplication.fake

import android.util.Log
import com.example.fruitapplication.data.FruitRepository
import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.network.asDomainObject
import com.example.fruitapplication.network.getFruitAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException

class FakeApiFruitRepository : FruitRepository {
    override suspend fun insert(item: Fruit) {

    }

    override suspend fun insertFruit(id: Int) {
    }

    override fun getFruits(): Flow<List<Fruit>> = flow {
        emit(
            FakeDataSource.apiFruits.map { it.asDomainObject() }
        )
    }

    override fun getFruit(id: Int): Flow<Fruit> = flow {
        FakeDataSource.apiFruits.map { it.asDomainObject() }.find { it.id == id }
    }

    override suspend fun refresh() {

    }

}