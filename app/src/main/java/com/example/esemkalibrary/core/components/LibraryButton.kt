package com.example.esemkalibrary.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.ui.theme.MudBrown

@Composable
fun LibraryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Button"
) {
    Button(
        onClick = onClick,
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MudBrown,
        ),
        elevation = elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        )
    ) {
        Text(
            text = text,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}