package com.example.fruitapplication.network

import androidx.room.PrimaryKey
import com.example.fruitapplication.model.Nutritions
import kotlinx.serialization.Serializable

/**
 * Serializable class representing nutritional information fetched from the API.
 *
 * @param calories The amount of calories in the nutritional information.
 * @param fat The amount of fat in the nutritional information.
 * @param sugar The amount of sugar in the nutritional information.
 * @param carbohydrates The amount of carbohydrates in the nutritional information.
 * @param protein The amount of protein in the nutritional information.
 */
@Serializable
data class ApiNutritions(
    @PrimaryKey(autoGenerate = true)
    val calories: Double,
    val fat: Double,
    val sugar: Double,
    val carbohydrates: Double,
    val protein: Double,
)

/**
 * Extension function converting [ApiNutritions] to its corresponding [Nutritions] domain object.
 *
 * @return Nutritions The domain object representing the nutritional information.
 */
fun ApiNutritions.asDomainObject(): Nutritions {
    return Nutritions(
        calories = calories,
        fat = fat,
        sugar = sugar,
        carbohydrates = carbohydrates,
        protein = protein,
    )
}

/**
 * Extension function converting a list of [ApiNutritions] to a list of [Nutritions] domain objects.
 *
 * @return List<Nutritions> A list of domain objects representing the nutritional information.
 */
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