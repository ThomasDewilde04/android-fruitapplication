package com.example.fruitapplication.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.model.Nutritions

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