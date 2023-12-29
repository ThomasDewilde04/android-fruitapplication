package com.example.fruitapplication.ui.fruitsScreen

sealed interface FruitApiState {
    object Loading : FruitApiState
    object Error : FruitApiState
    object Success : FruitApiState
}