package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.theme.Grey
import com.example.esemkalibrary.feature_forum.data.Reply
import java.time.format.DateTimeFormatter

@Composable
fun ReplyCard(modifier: Modifier = Modifier, reply: Reply, canBeDeleted: Boolean = false, onDeleteClicked: () -> Unit = {}) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5))
            .border(BorderStroke(width = 1.dp, color = Grey.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(5))
            .background(color = Color.White)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.photoprofiledefault),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .border(BorderStroke(width = 1.dp, color = Grey.copy(0.5f)), shape = CircleShape)
                    .size(64.dp)
            )
            Column(modifier = Modifier.widthIn(max = 200.dp), Arrangement.SpaceAround){
                Text(
                    text  = "${reply.createdBy.name} - ${reply.createdAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}",
                    fontSize = 14.sp
                )
                Text(
                    text = reply.message,
                    fontSize = 12.sp
                )
            }
        }
        if (canBeDeleted) {
            LibraryButton(onClick = {
                onDeleteClicked()
            }, text = "Remove")
        }
    }
}