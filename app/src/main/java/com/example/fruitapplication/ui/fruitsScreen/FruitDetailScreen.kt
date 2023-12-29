package com.example.fruitapplication.ui.fruitsScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fruitapplication.model.Fruit
import androidx.compose.ui.text.buildAnnotatedString

@Composable
fun FruitDetailFruitScreen(
    innerPadding: PaddingValues,
    fruit: Fruit,
    onBack: () -> Unit,
    fruitsScreenViewModel: FruitsScreenViewModel = viewModel(factory = FruitsScreenViewModel.Factory),
) {
    val fruitState = fruitsScreenViewModel.fruitState.collectAsState()
    val fruitApiState = fruitsScreenViewModel.fruitApiState

    LaunchedEffect(Unit) {
        fruitsScreenViewModel.getFruit(fruit.id)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            IconButton(
                onClick = { onBack() },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF72C444),
                )
            }
        }
        when (fruitApiState) {
            FruitApiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Could not load fruit",
                        color = Color(0xFF72C444),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                    )
                }
            }
            FruitApiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Loading fruit...",
                        color = Color(0xFF72C444),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                    )
                }
            }
            FruitApiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = fruitState.value.name,
                        color = Color(0xFF72C444),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 16.dp,
                                bottom = 16.dp
                            ),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = buildAnnotatedString {
                                pushStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF72C444)))
                                append("Fruit family: ")
                                pop()
                                append(fruitState.value.family)
                            },
                            fontSize = 16.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 10.dp
                                ),
                        )
                        Text(
                            text = buildAnnotatedString {
                                pushStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF72C444)))
                                append("Fruit order: ")
                                pop()
                                append(fruitState.value.order)
                            },
                            fontSize = 16.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 10.dp
                                ),
                        )
                        Text(
                            text = buildAnnotatedString {
                                pushStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF72C444)))
                                append("Fruit genus: ")
                                pop()
                                append(fruitState.value.genus)
                            },
                            fontSize = 16.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 10.dp
                                ),
                        )
                        Column {
                            Text(
                                text = "Fruit nutritions:",
                                color = Color(0xFF72C444),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 10.dp
                                    ),
                            )
                            Text(
                                text = "Calories: ${fruitState.value.nutritions?.calories}",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 40.dp,
                                        vertical = 6.dp
                                    ),
                            )
                            Text(
                                text = "Fat: ${fruitState.value.nutritions?.fat}",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 40.dp,
                                        vertical = 6.dp
                                    ),
                            )
                            Text(
                                text = "Sugar: ${fruitState.value.nutritions?.sugar}",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 40.dp,
                                        vertical = 6.dp
                                    ),
                            )
                            Text(
                                text = "Carbohydrates: ${fruitState.value.nutritions?.carbohydrates}",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 40.dp,
                                        vertical = 6.dp
                                    ),
                            )
                            Text(
                                text = "Protein: ${fruitState.value.nutritions?.protein}",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 40.dp,
                                        vertical = 6.dp
                                    ),
                            )
                        }
                }
            }
        }
    }
}