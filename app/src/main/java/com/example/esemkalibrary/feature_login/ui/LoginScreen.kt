package com.example.esemkalibrary.feature_login.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryPasswordTextField
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.navigation.Screen
import java.security.spec.ECField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: LoginViewModel = viewModel(factory = com.example.esemkalibrary.core.utils.viewModelFactory {
        LoginViewModel(LocalContext.current)
    })

    val ctx = LocalContext.current
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

                Log.e("TAG", "LoginScreen: ${uiState.value}", )
                viewModel.getAndSaveToken()

                if (!uiState.value.isAllowedToLogin) {
                    Toast.makeText(ctx, "Incorrect email or password", Toast.LENGTH_SHORT).show()
                } else {
                    navController.navigate(route = Screen.Main.route) {
                        popUpTo(route = Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(4.dp))
        LibraryButton(text = "Sign Up", onClick = {
            navController.navigate(Screen.SignUp.route)
        }, modifier = Modifier.fillMaxWidth())
    }
}