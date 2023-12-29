package com.example.fruitapplication.model

data class Fruit(
    var name: String,
    var id: Int,
    var family: String,
    var order: String,
    var genus: String,
    var nutritions: Nutritions?,
)