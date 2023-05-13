package com.example.esemkalibrary.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.ui.theme.Grey

@Composable
fun LibraryPasswordTextField(
    modifier: Modifier = Modifier,
    labelText: String = "Label",
    showPassword: Boolean = false,
    onShowPasswordChange: () -> Unit,
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(labelText, modifier.align(Alignment.Start))
        OutlinedTextField(value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Grey,
                unfocusedBorderColor = Grey,
                backgroundColor = White,
                cursorColor = Grey
            ),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = isError,
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = onShowPasswordChange) {
                        Icon(Icons.Filled.VisibilityOff, "Hide Password")
                    }
                } else {
                    IconButton(onClick = onShowPasswordChange) {
                        Icon(Icons.Filled.Visibility, "Show Password")
                    }
                }
            }
        )
    }
}