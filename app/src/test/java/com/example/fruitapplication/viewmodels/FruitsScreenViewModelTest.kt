package com.example.fruitapplication.viewmodels

import com.example.fruitapplication.TestDispatcherRule
import com.example.fruitapplication.fake.FakeApiFruitRepository
import com.example.fruitapplication.ui.fruitsScreen.FruitsScreenViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FruitsScreenViewModelTest {
    private lateinit var viewModel: FruitsScreenViewModel

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        viewModel = FruitsScreenViewModel(
            fruitRepository = FakeApiFruitRepository(),
        )
    }

    @Test
    fun correctFruitGetsCalled() {
        assertEquals(viewModel.getFruit(1) , FakeApiFruitRepository().getFruit(1))
    }

}