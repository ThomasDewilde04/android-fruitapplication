package com.example.fruitapplication.network

import androidx.room.PrimaryKey
import com.example.fruitapplication.model.Nutritions
import kotlinx.serialization.Serializable

@Serializable
data class ApiNutritions(
    @PrimaryKey(autoGenerate = true)
    val calories: Double,
    val fat: Double,
    val sugar: Double,
    val carbohydrates: Double,
    val protein: Double,
)

fun ApiNutritions.asDomainObject(): Nutritions {
    return Nutritions(
        calories = calories,
        fat = fat,
        sugar = sugar,
        carbohydrates = carbohydrates,
        protein = protein,
    )
}

fun List<ApiNutritions>.asDomainObjects(): List<Nutritions> {
    return this.map {
        Nutritions(
            calories = it.calories,
            fat = it.fat,
            sugar = it.sugar,
            carbohydrates = it.carbohydrates,
            protein = it.protein,
        )
    }
}