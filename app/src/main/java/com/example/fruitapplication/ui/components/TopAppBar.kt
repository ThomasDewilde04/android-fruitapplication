package com.example.fruitapplication.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int?,
) {
    title?.let {
        androidx.compose.material3.TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = stringResource(it),
                    color = Color(0xFF72C444),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 30.sp,
                )
            },
        )
    }
}

