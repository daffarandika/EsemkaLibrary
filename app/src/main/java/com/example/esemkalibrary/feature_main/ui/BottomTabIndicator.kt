package com.example.esemkalibrary.feature_main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.theme.DirtBrown

@Composable
fun BottomTabIndicator(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .background(color = DirtBrown)
        .border(width = 1.dp, color = Color.Black)
        .clickable {onClick()}
        .padding(8.dp)
    ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text)
    }
}