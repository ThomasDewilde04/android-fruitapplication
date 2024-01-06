package com.example.fruitapplication.ui.aboutScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fruitapplication.R
import com.example.fruitapplication.ui.components.FruitImageRow

/**
 * Composable function representing the detail screen of the about section in the Fruit Application.
 *
 * @param aboutViewModel The ViewModel responsible for handling the about screen functionality.
 * @param innerPadding Padding values applied within the content of the detail screen.
 */
@Composable
fun DetailScreen(
    aboutViewModel: AboutViewModel = viewModel(),
    innerPadding: PaddingValues,
) {
    About(
        innerPadding = innerPadding,
    )
}

/**
 * Composable function representing the About section in the Fruit Application.
 *
 * @param innerPadding Padding values applied within the content of the About section.
 */
@Composable
fun About(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 10.dp,
                    bottom = 10.dp,
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            FruitImageRow(
                img1 = R.mipmap.apple,
                img2 = R.mipmap.grape ,
                img3 = R.mipmap.orange
            )
            Text(
                text = "Fruit Application\n" +
                        "Version 1.0.0\n" +
                        "Developed by: \n" +
                        "Thomas Dewilde\n",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        top = 30.dp,
                    ),
                    color = Color(0xFF72C344),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 45.sp

            )
            FruitImageRow(
                img1 = R.mipmap.banana,
                img2 = R.mipmap.cherry ,
                img3 = R.mipmap.watermelon
            )
        }
    }
}
