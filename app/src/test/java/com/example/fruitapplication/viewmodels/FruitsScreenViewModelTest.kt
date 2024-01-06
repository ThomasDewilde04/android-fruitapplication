package com.example.fruitapplication.viewmodels

import com.example.fruitapplication.data.FruitRepository
import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.ui.fruitsScreen.FruitApiState
import com.example.fruitapplication.ui.fruitsScreen.FruitsScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class FruitsScreenViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var fruitRepository: FruitRepository
    private lateinit var viewModel: FruitsScreenViewModel

    @Before
    fun setup() {
        fruitRepository = mock(FruitRepository::class.java)
        viewModel = FruitsScreenViewModel(fruitRepository)
    }

    @Test
    fun getRepoFruitsTestSucces()  {
        val testFruitsList = listOf(
            Fruit("Apple", 1, "Rosaceae", "Rosales", "Malus", null),
            Fruit("Orange", 2, "Rutaceae", "Sapindales", "Citrus", null)
        )

        val mockFlow = MutableStateFlow(testFruitsList)
        `when`(fruitRepository.getFruits()).thenReturn(mockFlow)

        viewModel.getRepoFruits()

        assertEquals(FruitApiState.Success, viewModel.fruitApiState)
    }

    @Test
    fun getFruitTestSuccess() {
        val testFruit = Fruit("Apple", 1, "Rosaceae", "Rosales", "Malus", null)

        val mockFlow = MutableStateFlow(testFruit)
        `when`(fruitRepository.getFruit(1)).thenReturn(mockFlow)

        viewModel.getFruit(1)

        assertEquals(FruitApiState.Success, viewModel.fruitApiState)
    }

}

class TestDispatcherRule (
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}