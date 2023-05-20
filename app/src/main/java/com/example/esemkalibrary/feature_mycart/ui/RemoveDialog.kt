package com.example.esemkalibrary.feature_mycart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.esemkalibrary.core.components.theme.MudBrown

@Composable
fun RemoveDialog(modifier: Modifier = Modifier, onNoClicked:() -> Unit, onYesClicked:() -> Unit, id: String) {
    Dialog(onDismissRequest = {onNoClicked()}, properties = DialogProperties()) {
        Column(modifier.wrapContentSize().background(color = MudBrown, shape = RoundedCornerShape(5))) {
            Text(
                text = "Are you sure you want to remove this book from your cart?",
                style = MaterialTheme.typography.h6,
                color = Color.White,
                modifier = Modifier.padding(16.dp),
            )
            Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Text(text = "Yes", modifier = Modifier.clickable{ onYesClicked() }, fontWeight = FontWeight.Bold, color = Color.White, style = MaterialTheme.typography.button)
                Spacer(Modifier.padding(8.dp))
                Text(text = "No", modifier = Modifier.clickable{ onNoClicked() }, fontWeight = FontWeight.Bold, color = Color.White, style = MaterialTheme.typography.button)
            }
        }
    }
}