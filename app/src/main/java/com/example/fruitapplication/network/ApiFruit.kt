package com.example.fruitapplication.network

import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.model.Nutritions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

/**
 * Serializable class representing fruit data obtained from the API.
 *
 * @param name The name of the fruit.
 * @param id The unique identifier of the fruit.
 * @param family The family to which the fruit belongs.
 * @param order The order to which the fruit belongs.
 * @param genus The genus of the fruit.
 * @param nutritions Nutritional information of the fruit (nullable).
 */
@Serializable
data class ApiFruit(
    val name: String,
    val id: Int,
    val family: String,
    val order: String,
    val genus: String,
    val nutritions: ApiNutritions?,
)

/**
 * Extension function converting a flow of [ApiFruit] to a flow of [Fruit] domain objects.
 *
 * @return Flow<List<Fruit>> A flow of domain objects representing fruit data.
 */
fun Flow<List<ApiFruit>>.asDomainObjects(): Flow<List<Fruit>> {
    return map {
        it.asDomainObjects()
    }
}

/**
 * Extension function converting a list of [ApiFruit] to a list of [Fruit] domain objects.
 *
 * @return List<Fruit> A list of domain objects representing fruit data.
 */
fun List<ApiFruit>.asDomainObjects(): List<Fruit> {
    var domainList = this.map {
        Fruit(it.name, it.id, it.family, it.order, it.genus, it.nutritions?.asDomainObject())
    }
    return domainList
}

/**
 * Extension function converting an [ApiFruit] to a [Fruit] domain object.
 *
 * @return Fruit The domain object representing fruit data.
 */
fun ApiFruit.asDomainObject(): Fruit {
    return Fruit(name, id, family, order, genus, nutritions?.asDomainObject())
}

