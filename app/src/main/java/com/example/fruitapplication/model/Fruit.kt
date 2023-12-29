package com.example.fruitapplication.model

/**
 * Data class representing a fruit entity.
 *
 * @property name The name of the fruit.
 * @property id The unique identifier of the fruit.
 * @property family The family to which the fruit belongs.
 * @property order The order to which the fruit belongs.
 * @property genus The genus of the fruit.
 * @property nutritions The nutritional information of the fruit.
 */
data class Fruit(
    var name: String,
    var id: Int,
    var family: String,
    var order: String,
    var genus: String,
    var nutritions: Nutritions?,
)