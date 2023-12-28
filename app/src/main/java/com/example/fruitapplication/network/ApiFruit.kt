package com.example.fruitapplication.network

import androidx.room.Embedded
import com.example.fruitapplication.model.Fruit
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
)

fun Flow<List<ApiFruit>>.asDomainObjects(): Flow<List<Fruit>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ApiFruit>.asDomainObjects(): List<Fruit> {
    var domainList = this.map {
        Fruit(it.name, it.id, it.family, it.order, it.genus)
    }
    return domainList
}