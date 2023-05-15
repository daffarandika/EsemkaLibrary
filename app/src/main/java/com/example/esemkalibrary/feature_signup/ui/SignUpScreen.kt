package com.example.esemkalibrary.feature_signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryPasswordTextField
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.SandBrown

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Column(modifier
        .background(color = SandBrown)
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Sign Up",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        LibraryTextField(value = "",
            onValueChange = {},
            labelText = "Name",
            modifier = Modifier.fillMaxWidth())
        LibraryPasswordTextField(onShowPasswordChange = { /*TODO*/ },
            value = "",
            onValueChange = {},
            labelText = "Password",
            modifier = Modifier.fillMaxWidth())
        LibraryPasswordTextField(onShowPasswordChange = { /*TODO*/ },
            value = "",
            onValueChange = {},
            labelText = "Confirm Password",
            modifier = Modifier.fillMaxWidth())
        LibraryPasswordTextField(onShowPasswordChange = { /*TODO*/ },
            value = "",
            onValueChange = {},
            labelText = "Email",
            modifier = Modifier.fillMaxWidth())
        LibraryButton(onClick = { /*TODO*/ }, text = "Sign Up", modifier = Modifier.fillMaxWidth())
    }
}