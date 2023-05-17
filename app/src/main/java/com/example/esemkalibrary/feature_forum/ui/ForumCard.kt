package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.LibraryButton

@Composable
fun ForumCard(modifier: Modifier, onClick: () -> Unit) {
    Row(modifier
        .padding(8.dp)
        .background(shape = RoundedCornerShape(10), color = Color.White)
        .clickable { onClick() },
        Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(8.dp), Arrangement.spacedBy(8.dp)) {
            Text("Title section")
            Text("Poster")
            Text("Latest: Poster, YYYY mm DD")
        }
        Column(Modifier.padding(8.dp).align(Alignment.Bottom)) {
            LibraryButton(onClick = { /*TODO*/ }, text = "Remove")
        }
    }
}