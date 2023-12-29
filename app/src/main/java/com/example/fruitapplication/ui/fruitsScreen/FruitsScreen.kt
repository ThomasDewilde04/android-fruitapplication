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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fruitapplication.R
import com.example.fruitapplication.model.Fruit
import com.example.fruitapplication.ui.components.Card

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

@Composable
fun FruitsList(
    fruitsScreenViewModel: FruitsScreenViewModel = viewModel(factory = FruitsScreenViewModel.Factory),
    innerPadding: PaddingValues,
    onFruitClick: (Fruit) -> Unit
) {
    val fruitListState by fruitsScreenViewModel.fruitListState.collectAsState()
    val fruitApiState = fruitsScreenViewModel.fruitApiState

    Column (
        modifier = Modifier.padding(innerPadding),
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        Welcome(modifier = Modifier)
        Spacer(modifier = Modifier.size(30.dp))
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
                FruitColumn(modifier = Modifier, fruitList = fruitListState, onFruitClick = onFruitClick)
            }
        }

    }
}

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
                .size(65.dp),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = "Welcome to the\nFruit app!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun FruitColumn(modifier: Modifier, fruitList: List<Fruit>, onFruitClick: (Fruit) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Fruits",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp),
        )
        Spacer(modifier = Modifier.height(6.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(25.dp))
        LazyColumn {
            items(fruitList) { fruit ->
                Card(name = fruit.name ,onClick = { onFruitClick(fruit) })
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}