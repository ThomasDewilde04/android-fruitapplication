package com.example.fruitapplication

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.fruitapplication.ui.FruitApp
import com.example.fruitapplication.ui.util.FruitNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * NavigationTest is a test class for navigating within the FruitApplication.
 */
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    /**
     * Sets up the application's navigation environment for testing.
     */
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            FruitApp(navController = navController, navigationType = FruitNavigationType.BOTTOM_NAVIGATION)
        }
    }

    /**
     * Verifies if the "About" destination is navigable and displayed.
     */
    @Test
    fun navigateToAbout() {
        composeTestRule
            .onNodeWithText("About")
            .assertIsDisplayed()
    }

    /**
     * Verifies if the start destination ("Fruits") is displayed.
     */
    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Fruits")
            .assertIsDisplayed()
    }
}