package com.example.esemkalibrary.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.theme.Grey

@Composable
fun LibraryTextField(
    modifier: Modifier = Modifier,
    labelText: String = "Label",
    value: String,
    isError: Boolean= false,
    onValueChange: (String) -> Unit,
    showLabel: Boolean = true,
    hint: @Composable () -> Unit = {},
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        if (showLabel){
            Text(labelText, Modifier.align(Alignment.Start))
        }
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Grey,
                unfocusedBorderColor = Grey,
                backgroundColor = White,
                cursorColor = Grey
            ),
            isError = isError,
            trailingIcon = {
                if (isError) {
                    Icon(Icons.Filled.Error, "Error")
                }
            },
            placeholder = hint,
        )
    }
}