package com.example.fruitapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FruitImageRow(img1: Int, img2: Int, img3: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = img1),
            contentDescription = "Image 1",
            modifier = Modifier
                .size(90.dp),
        )
        Image(
            painter = painterResource(id = img2),
            contentDescription = "Image 2",
            modifier = Modifier
                .size(90.dp),
        )
        Image(
            painter = painterResource(id = img3),
            contentDescription = "Image 3",
            modifier = Modifier
                .size(90.dp),
        )
    }
}