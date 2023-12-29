package com.example.fruitapplication.ui.fruitsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fruitapplication.R
import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.ui.components.Card

/**
 * Composable responsible for displaying the list of fruits and the details of a selected fruit.
 * It manages the UI states when interacting with fruits, including showing a list of fruits and their details.
 *
 * @param innerPadding Padding values for the internal layout.
 */
@Composable
fun FruitsScreen(
    innerPadding: PaddingValues,
) {
    var selectedFruit by remember { mutableStateOf<Fruit?>(null) }

    if (selectedFruit != null) {
        FruitDetailFruitScreen(
            innerPadding = innerPadding,
            fruit = selectedFruit!!,
            onBack = { selectedFruit = null },
        )
    } else {
        FruitsList(innerPadding = innerPadding) {
            selectedFruit = it
        }
    }
}


/**
 * Composable responsible for displaying the welcome composable and the list of fruits fetched from the API.
 * It manages the UI states such as loading, error, and success when retrieving fruit data.
 *
 * @param fruitsScreenViewModel The view model used for fetching fruit data.
 * @param innerPadding Padding values for the internal layout.
 * @param onFruitClick Callback function triggered when a fruit item is clicked.
 */
@Composable
fun FruitsList(
    fruitsScreenViewModel: FruitsScreenViewModel = viewModel(factory = FruitsScreenViewModel.Factory),
    innerPadding: PaddingValues,
    onFruitClick: (Fruit) -> Unit
) {
    val fruitListState by fruitsScreenViewModel.fruitListState.collectAsState()
    val fruitApiState = fruitsScreenViewModel.fruitApiState

    Column (
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
    ) {
        Welcome(modifier = Modifier.weight(1f))
        when (fruitApiState) {
            is FruitApiState.Error -> {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Data could not be fetched")
                }
            }
            is FruitApiState.Loading -> {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Loading...")
                }
            }
            is FruitApiState.Success -> {
                FruitColumn(modifier = Modifier.weight(2f), fruitList = fruitListState, onFruitClick = onFruitClick)
            }
        }

    }
}

/**
 * Composable displaying the welcome screen for the Fruit application.
 *
 * @param modifier Modifier for the layout of the welcome screen.
 */
@Composable
fun Welcome(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.strawberry),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp),
        )
        Text(
            text = "Welcome to the\nFruit app!",
            fontSize = 28.sp,
            color = Color(0xFF72C444),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * Composable responsible for displaying a column of fruits.
 *
 * @param modifier Modifier for the layout of the fruit column.
 * @param fruitList The list of fruits to be displayed.
 * @param onFruitClick Callback function triggered when a fruit item is clicked.
 */
@Composable
fun FruitColumn(modifier: Modifier, fruitList: List<Fruit>, onFruitClick: (Fruit) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Fruits",
            color = Color(0xFF72C444),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(fruitList) { fruit ->
                Card(name = fruit.name, onClick = { onFruitClick(fruit) })
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}