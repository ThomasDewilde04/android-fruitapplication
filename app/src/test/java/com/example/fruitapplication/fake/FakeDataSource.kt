package com.example.fruitapplication.fake

import com.example.fruitapplication.network.ApiFruit

object FakeDataSource {
    const val fruitNameOne = "testApple"
    const val fruitIdOne = 1
    const val fruitFamilyOne = "testAppleFamily"
    const val fruitOrderOne = "testAppleOrder"
    const val fruitGenusOne = "testAppleGenus"
    val fruitNutritionsOne = null

    const val fruitNameTwo = "testBanana"
    const val fruitIdTwo = 2
    const val fruitFamilyTwo = "testBananaFamily"
    const val fruitOrderTwo = "testBananaOrder"
    const val fruitGenusTwo = "testBananaGenus"
    val fruitNutritionsTwo = null

    val apiFruits = listOf(
        ApiFruit(fruitNameOne, fruitIdOne, fruitFamilyOne, fruitOrderOne, fruitGenusOne, fruitNutritionsOne),
        ApiFruit(fruitNameTwo, fruitIdTwo, fruitFamilyTwo, fruitOrderTwo, fruitGenusTwo, fruitNutritionsTwo),
    )
}