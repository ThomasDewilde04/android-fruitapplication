package com.example.fruitapplication.network

import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.model.Nutritions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiFruit(
    val name: String,
    val id: Int,
    val family: String,
    val order: String,
    val genus: String,
    val nutritions: ApiNutritions?,
)

fun Flow<List<ApiFruit>>.asDomainObjects(): Flow<List<Fruit>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiFruit>.asDomainObjects(): List<Fruit> {
    var domainList = this.map {
        Fruit(it.name, it.id, it.family, it.order, it.genus, it.nutritions?.asDomainObject())
    }
    return domainList
}

fun ApiFruit.asDomainObject(): Fruit {
    return Fruit(name, id, family, order, genus, nutritions?.asDomainObject())
}

