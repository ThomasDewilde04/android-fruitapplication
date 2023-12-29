package com.example.fruitapplication.ui.fruitsScreen

import com.example.fruitapplication.FruitsApplication
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fruitapplication.data.FruitRepository
import com.example.fruitapplication.model.Fruit
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class FruitsScreenViewModel (
    private val fruitRepository: FruitRepository
) : ViewModel() {
    var fruitApiState: FruitApiState by mutableStateOf(FruitApiState.Loading)
        private set

    lateinit var fruitListState: StateFlow<List<Fruit>>
    lateinit var fruitState: StateFlow<Fruit>

    init {
        getRepoFruits()
        getFruit(1)
        Log.i("vm inspection", "FruitsScreenViewModel init")
    }

    fun getFruit(id: Int) {
        try {
            fruitApiState = FruitApiState.Loading
            viewModelScope.launch {
                fruitRepository.insertFruit(id)
                fruitState = fruitRepository.getFruit(id).stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = Fruit(
                        name = "",
                        id = 0,
                        family = "",
                        order = "",
                        genus = "",
                        nutritions = null,
                    ),
                )
                fruitApiState = FruitApiState.Success
            }
        } catch (e: IOException) {
            fruitApiState = FruitApiState.Error
        }
    }

    private fun getRepoFruits() {
        try {
            viewModelScope.launch { fruitRepository.refresh() }
            fruitListState = fruitRepository.getFruits().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf(),
            )
            fruitApiState = FruitApiState.Success
        } catch (e: IOException) {
            fruitApiState = FruitApiState.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FruitsApplication)
                val fruitRepository = application.container.fruitRepository
                FruitsScreenViewModel(fruitRepository)
            }
        }
    }

}

