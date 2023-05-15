package com.example.esemkalibrary.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.theme.DirtBrown
import com.example.esemkalibrary.core.components.theme.MudBrown

@Composable
fun LibraryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "Button"
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DirtBrown,
        ),
        elevation = elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
        border = BorderStroke(1.dp, MudBrown)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}