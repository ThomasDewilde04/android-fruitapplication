package com.example.fruitapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fruitapplication.model.Fruit

@Entity(tableName = "fruit")
data class dbFruit(
    val name: String = "",
    @PrimaryKey
    val id: Int,
    val family: String = "",
    val order: String = "",
    val genus: String = "",
)

fun dbFruit.asDomainFruit(): Fruit {
    return Fruit(
        this.name,
        this.id,
        this.family,
        this.order,
        this.genus,
    )
}

fun Fruit.asDbFruit(): dbFruit {
    return dbFruit(
        name = this.name,
        id = this.id,
        family = this.family,
        order = this.order,
        genus = this.genus,
    )
}


fun List<dbFruit>.asDomainFruits(): List<Fruit> {
    var fruitList = this.map {
        Fruit(it.name, it.id, it.family, it.order, it.genus)
    }
    return fruitList
}