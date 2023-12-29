package com.example.fruitapplication.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.model.Nutritions

/**
 * Represents the database entity for storing fruit-related information.
 * @property name The name of the fruit.
 * @property id The unique identifier of the fruit.
 * @property family The family of the fruit.
 * @property order The order of the fruit.
 * @property genus The genus of the fruit.
 * @property nutritions The nutritional information of the fruit, embedded as [Nutritions].
 */
@Entity(tableName = "fruit")
data class dbFruit(
    @PrimaryKey
    val name: String,
    val id: Int,
    val family: String,
    val order: String,
    val genus: String,
    @Embedded
    val nutritions: Nutritions?,
)

/**
 * Converts a [dbFruit] entity object into its equivalent [Fruit] domain object.
 * @return The [Fruit] domain object converted from this [dbFruit].
 */
fun dbFruit.asDomainFruit(): Fruit {
    return Fruit(
        name = this.name,
        id = this.id,
        family = this.family,
        order = this.order,
        genus = this.genus,
        nutritions = this.nutritions,
    )
}

/**
 * Converts a [Fruit] domain object into its equivalent [dbFruit] entity object.
 * @return The [dbFruit] entity object converted from this [Fruit].
 */
fun Fruit.asDbFruit(): dbFruit {
    return dbFruit(
        name = this.name,
        id = this.id,
        family = this.family,
        order = this.order,
        genus = this.genus,
        nutritions = this.nutritions,
    )
}

/**
 * Converts a list of [dbFruit] entity objects into a list of [Fruit] domain objects.
 * @return The list of [Fruit] domain objects converted from the list of [dbFruit] objects.
 */
fun List<dbFruit>.asDomainFruits(): List<Fruit> {
    var fruitList = this.map {
        Fruit(
            it.name,
            it.id,
            it.family,
            it.order,
            it.genus,
            it.nutritions,
        )
    }
    return fruitList
}