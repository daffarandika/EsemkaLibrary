package com.example.esemkalibrary.feature_login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val viewModel: LoginViewModel = viewModel()
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(SandBrown)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val uiState = viewModel.uiState.collectAsState()

        Image(
            painterResource(id = R.drawable.favicon),
            "Logo"
        )

        LibraryTextField(value = uiState.value.email,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                viewModel.updateEmailInput(it)
            },
            isError = uiState.value.isEmailError,
            labelText = "Email")
        Spacer(Modifier.size(4.dp))
        LibraryPasswordTextField(value = uiState.value.password,
            onValueChange = {
                viewModel.updatePasswordInput(it)
            },
            modifier = Modifier.fillMaxWidth(),
            labelText = "Password",
            onShowPasswordChange = {
                viewModel.updatePasswordVisibility(!uiState.value.isPasswordVisible)
            },
            showPassword = uiState.value.isPasswordVisible,
            isError = uiState.value.isPasswordError
        )
        Spacer(Modifier.size(16.dp))
        LibraryButton(text = "Login", onClick = {
            if (!viewModel.isReadyToLogin()) {
                if (uiState.value.password.isBlank()) viewModel.updatePasswordError(true) else viewModel.updatePasswordError(false)
                if (uiState.value.email.isBlank()) viewModel.updateEmailError(true) else viewModel.updateEmailError(false)
            } else {
                viewModel.getToken()
                onSuccessfulLogin()
            }
            }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(4.dp))
        LibraryButton(text = "Sign Up", onClick = onSignUpClicked, modifier = Modifier.fillMaxWidth())
    }
}