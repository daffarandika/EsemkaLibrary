package com.example.esemkalibrary.feature_login.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryPasswordTextField
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.SandBrown

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onSuccessfulLogin:() -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(SandBrown)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var showPassword by remember {
            mutableStateOf(false)
        }
        var isEmailError by remember {
            mutableStateOf(false)
        }
        var isPasswordError by remember {
            mutableStateOf(false)
        }
        Image(
            painterResource(id = R.drawable.favicon),
            "Logo"
        )

        LibraryTextField(value = email,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                email = it
            },
            isError = isEmailError,
            labelText = "Email")
        Spacer(Modifier.size(4.dp))
        LibraryPasswordTextField(value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            labelText = "Password",
            onShowPasswordChange = {
                showPassword = !showPassword
            },
            showPassword = showPassword,
            isError = isPasswordError
        )
        Spacer(Modifier.size(16.dp))
        LibraryButton(text = "Login", onClick = {
//            isEmailError = email.isBlank()
//            isPasswordError = password.isBlank()
//            if (!(isPasswordError or isEmailError)) {
//                onSuccessfulLogin()
//            }
                onSuccessfulLogin()
            }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(4.dp))
        LibraryButton(text = "Sign Up", onClick = onSignUpClicked, modifier = Modifier.fillMaxWidth())
    }
}