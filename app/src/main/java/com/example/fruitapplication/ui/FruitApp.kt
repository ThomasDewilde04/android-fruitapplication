package com.example.fruitapplication.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fruitapplication.R
import com.example.fruitapplication.ui.aboutScreen.DetailScreen
import com.example.fruitapplication.ui.components.FruitNavigationRail
import com.example.fruitapplication.ui.components.NavigationDrawerContent
import com.example.fruitapplication.ui.fruitsScreen.FruitsScreen
import com.example.fruitapplication.ui.navigation.BottomAppBar
import com.example.fruitapplication.ui.navigation.Destinations
import com.example.fruitapplication.ui.navigation.TopAppBar
import com.example.fruitapplication.ui.util.FruitNavigationType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitApp(
    navigationType: FruitNavigationType,
    navController: NavHostController = rememberNavController(),
    ) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Fruits", "About")
    val icons = listOf(Icons.Outlined.MenuBook, Icons.Outlined.Info )

    if (navigationType == FruitNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(drawerContent = {
            PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                NavigationDrawerContent(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.inverseOnSurface)
                        .padding(dimensionResource(R.dimen.drawer_padding_content)),
                )
            }
        }) {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        modifier = Modifier.padding(8.dp),
                        title = when (selectedItem) {
                            1 -> R.string.about
                            else -> null
                        },
                    )
                },
            ) { innerPadding ->
                NavHost(navController = navController, startDestination = Destinations.Fruits.name) {
                    composable(route = Destinations.Fruits.name) {
                        FruitsScreen(innerPadding = innerPadding)
                    }
                    composable(route = Destinations.About.name) {
                        DetailScreen(innerPadding = innerPadding)
                    }
                }
            }
        }
    } else if (navigationType == FruitNavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.padding(8.dp),
                    title = when (selectedItem) {
                        1 -> R.string.about
                        else -> null
                    },
                )
            },
            bottomBar = {
                BottomAppBar(
                    items = items,
                    icons = icons,
                    selectedItem = selectedItem,
                    onItemSelected = { index ->
                        selectedItem = index
                        navController.navigate(Destinations.values()[index].name)
                    },
                )
            }
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = Destinations.Fruits.name) {
                composable(route = Destinations.Fruits.name) {
                    FruitsScreen(innerPadding = innerPadding)
                }
                composable(route = Destinations.About.name) {
                    DetailScreen(innerPadding = innerPadding)
                }
            }
        }
    } else {
        Row {
            AnimatedVisibility(visible = navigationType == FruitNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                FruitNavigationRail(
                    selectedDestination = navController.currentDestination,
                    onTabPressed = { node: String -> navController.navigate(node) },
                )
            }
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        modifier = Modifier.padding(8.dp),
                        title = when (selectedItem) {
                            1 -> R.string.about
                            else -> null
                        },
                    )
                },
            ) { innerPadding ->
                NavHost(navController = navController, startDestination = Destinations.Fruits.name) {
                    composable(route = Destinations.Fruits.name) {
                        FruitsScreen(innerPadding = innerPadding)
                    }
                    composable(route = Destinations.About.name) {
                        DetailScreen(innerPadding = innerPadding)
                    }
                }
            }
        }
    }
}