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
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.feature_forum.data.ForumItem
import java.time.format.DateTimeFormatter

@Composable
fun ForumCard(modifier: Modifier, onBodyClicked: () -> Unit, forumItem: ForumItem, onRemoveClicked:() -> Unit =
    {}, canBeDeleted: Boolean = false) {
    Row(modifier
        .padding(8.dp)
        .background(shape = RoundedCornerShape(10), color = Color.White)
        .clickable { onBodyClicked() },
        Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(8.dp), Arrangement.spacedBy(4.dp)) {
            Text(text = forumItem.subject, fontSize = 18.sp)
            Text(forumItem.createdBy.name)
            Text("Latest: ${forumItem.createdAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}")
        }
        if (canBeDeleted){
            Column(Modifier.padding(8.dp).align(Alignment.Bottom)) {
                LibraryButton(onClick = {
                    onRemoveClicked()
                }, text = "Remove")
            }
        }
    }
}