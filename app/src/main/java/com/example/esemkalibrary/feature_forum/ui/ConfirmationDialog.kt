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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.esemkalibrary.core.components.theme.DirtBrown

@Composable
fun ConfirmationDialog(onYesClicked:() -> Unit, onNoClicked: () -> Unit) {
    Dialog(onDismissRequest = { onNoClicked() }, properties = DialogProperties()) {
        Column(Modifier
            .wrapContentSize()
            .background(color = DirtBrown, shape = RoundedCornerShape(5))) {
            Text(
                text = "Are you sure you want to delete this reply ?",
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End), horizontalArrangement = Arrangement.End) {
                Text(text = "Yes", color = Color.White, fontSize = 12.sp, modifier = Modifier.clickable {
                    onYesClicked()
                })
                Spacer(Modifier.width(8.dp))
                Text(text = "No", color = Color.White, fontSize = 12.sp, modifier = Modifier.clickable {
                    onNoClicked()
                })
            }
        }
    }
}