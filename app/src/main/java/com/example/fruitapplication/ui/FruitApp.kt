package com.example.fruitapplication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fruitapplication.R
import com.example.fruitapplication.ui.detailScreen.DetailScreen
import com.example.fruitapplication.ui.fruitsScreen.FruitsScreen
import com.example.fruitapplication.ui.navigation.BottomAppBar
import com.example.fruitapplication.ui.navigation.Destinations
import com.example.fruitapplication.ui.navigation.TopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitApp(
    navController: NavHostController = rememberNavController(),
    ) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Fruits", "Detail")
    val icons = listOf(Icons.Outlined.MenuBook, Icons.Outlined.Info )

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(8.dp),
                title = when (selectedItem) {
                    1 -> R.string.detail
                    else -> null // No title, will result in no TopAppBar being rendered
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
        NavHost(navController = navController, startDestination = Destinations.Start.name) {
            composable(route = Destinations.Start.name) {
                FruitsScreen(innerPadding = innerPadding)
            }
            composable(route = Destinations.Detail.name) {
                DetailScreen(innerPadding = innerPadding)
            }
        }
    }
}