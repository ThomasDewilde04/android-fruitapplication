package com.example.fruitapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Composable representing a custom card UI component.
 *
 * @param name The text content displayed within the card.
 * @param onClick The optional callback function invoked when the card is clicked.
 */
@Composable
fun Card(name: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .clickable { onClick() }
            .background(
                color = Color(0xFF72C444),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(all = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        CardContent(name)
    }
}

/**
 * Composable representing the content displayed within a custom card component.
 *
 * @param name The text content displayed within the card.
 */
@Composable
fun CardContent(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}