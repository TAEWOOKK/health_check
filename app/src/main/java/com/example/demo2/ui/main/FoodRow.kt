package com.example.demo2.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.demo2.data.model.FoodItem


@Composable
fun FoodRow(food: FoodItem, onClick: () -> Unit) {
    val buttonColor by animateColorAsState(
        targetValue = if (food.remaining == 0) Color.Gray else MaterialTheme.colorScheme.primary
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = if (food.remaining == 0) Color(0xFFDFF0D8) else Color(0xFFF0F0F0)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "${food.name}: ${food.remaining} / ${food.totalCount}",
                modifier = Modifier.weight(1f),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                enabled = food.remaining > 0
            ) {
                Text(if (food.remaining == 0) "완료" else "먹음", color = Color.White)
            }
        }
    }
}