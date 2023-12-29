package com.example.fruitapplication.ui.fruitsScreen

/**
 * Represents different states of API response when fetching fruit data in the Fruit screen of the application.
 */
sealed interface FruitApiState {

    /**
     * Represents the loading state while fetching data from the API.
     */
    object Loading : FruitApiState

    /**
     * Represents the error state when there's an issue fetching data from the API.
     */
    object Error : FruitApiState

    /**
     * Represents the success state when data is successfully fetched from the API.
     */
    object Success : FruitApiState
}