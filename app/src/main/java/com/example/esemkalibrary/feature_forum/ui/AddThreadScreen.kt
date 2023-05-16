package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.SandBrown

@Composable
fun AddThreadScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SandBrown)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var text by remember {
            mutableStateOf("")
        }
        LibraryTextField(value = "", onValueChange = {text = it}, labelText = "Subject", modifier = modifier.fillMaxWidth())
        LibraryTextField(value = text, onValueChange = {
              text = it
        }, modifier = Modifier
            .height(256.dp)
            .fillMaxWidth(),
            labelText = "Body"
        )
        LibraryButton(onClick = { /*TODO*/ }, text = "Add thread", modifier = Modifier.fillMaxWidth())
    }
}