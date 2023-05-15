package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.theme.SandBrown

@Composable
fun ThreadDetailScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
        .background(color = SandBrown)
        .fillMaxWidth()
    ) {
        LazyColumn(modifier
            .weight(weight = 1f, fill = false)
            .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            item {
                PostCard()
            }
            items(7) {
                ReplyCard()
            }
        }
    }
}