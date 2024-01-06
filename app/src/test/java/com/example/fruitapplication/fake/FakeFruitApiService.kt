package com.example.fruitapplication.fake

import com.example.fruitapplication.network.ApiFruit
import com.example.fruitapplication.network.FruitApiService

class FakeFruitApiService : FruitApiService {
    override suspend fun getAllFruits(): List<ApiFruit> {
        return FakeDataSource.apiFruits
    }

    override suspend fun getFruit(id: Int): ApiFruit {
        return FakeDataSource.apiFruits.find { it.id == id }!!
    }


}