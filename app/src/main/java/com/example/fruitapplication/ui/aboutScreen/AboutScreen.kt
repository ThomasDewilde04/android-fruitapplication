package com.example.fruitapplication.ui.aboutScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapplication.R
import com.example.fruitapplication.ui.components.FruitImageRow

@Composable
fun DetailScreen(
    innerPadding: PaddingValues,
) {
    About(
        innerPadding = innerPadding,
    )
}

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
                    top = 160.dp,
                    bottom = 90.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FruitImageRow(
                img1 = R.drawable.apple,
                img2 = R.drawable.grape ,
                img3 = R.drawable.orange
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
                img1 = R.drawable.banana,
                img2 = R.drawable.cherry ,
                img3 = R.drawable.watermelon
            )
        }
    }
}
