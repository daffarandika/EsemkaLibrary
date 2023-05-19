package com.example.esemkalibrary.feature_mycart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DateLabel(modifier: Modifier = Modifier, date: LocalDate = LocalDate.now(), onClick: () -> Unit = {}) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5))
            .background(color = Color.White, shape = RoundedCornerShape(5))
            .border(
                color = Color.Gray,
                shape = RoundedCornerShape(5),
                width = 1.dp
            )
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        )
    }
}