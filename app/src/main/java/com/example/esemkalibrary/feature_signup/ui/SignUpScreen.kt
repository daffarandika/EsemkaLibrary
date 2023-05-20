package com.example.esemkalibrary.feature_signup.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryPasswordTextField
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.navigation.Screen

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel: SignUpViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val ctx = LocalContext.current
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
        LibraryTextField(value = uiState.name,
            onValueChange = {
                            viewModel.updateNameInput(it)
            },
            labelText = "Name",
            isError = uiState.isNameError,
            modifier = Modifier.fillMaxWidth())
        LibraryPasswordTextField(onShowPasswordChange = {
                viewModel.updatePasswordVisibility(!uiState.isPasswordVisible)
            },
            value = uiState.password,
            onValueChange = {viewModel.updatePasswordInput(it)},
            showPassword = uiState.isPasswordVisible,
            isError = uiState.isPasswordError,
            labelText = "Password",
            modifier = Modifier.fillMaxWidth())
        LibraryPasswordTextField(onShowPasswordChange = { viewModel.updateCPasswordVisibility(!uiState.isCPasswordVisible) },
            value = uiState.cPassword,
            onValueChange = {viewModel.updateCPasswordInput(it)},
            labelText = "Confirm Password",
            isError = uiState.isCPasswordError,
            showPassword = uiState.isCPasswordVisible,
            modifier = Modifier.fillMaxWidth())
        LibraryTextField(value = uiState.email,
            onValueChange = {viewModel.updateEmailInput(it)},
            labelText = "Email",
            isError = uiState.isEmailError,
            modifier = Modifier.fillMaxWidth())
        LibraryButton(
            onClick = {
                Toast.makeText(ctx, "Successfully signed up, please log in", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Login.route) {
                        inclusive = true
                    }
                }
                      if (!viewModel.isReadyToSignUp()) {
                          if (!viewModel.isEverythingFilled()) {
                              if (uiState.name.isBlank()) viewModel.updateNameError(true) else viewModel.updateNameError(false)
                              if (uiState.password.isBlank()) viewModel.updatePasswordError(true) else viewModel.updatePasswordError(false)
                              if (uiState.cPassword.isBlank()) viewModel.updateCPasswordError(true) else viewModel.updateCPasswordError(false)
                              if (uiState.email.isBlank()) viewModel.updateEmailError(true) else viewModel.updateEmailError(false)
                          }
                          if (viewModel.passwordsDoNotMatch()){
                              viewModel.updatePasswordError(true)
                              viewModel.updateCPasswordError(true)
                              Toast.makeText(ctx, "Password and password confirmation does not match", Toast.LENGTH_SHORT).show()
                          } else {
                              viewModel.updatePasswordError(false)
                              viewModel.updateCPasswordError(false)
                          }
                          if (!viewModel.isEmailValid()) {
                              viewModel.updateEmailError(true)
                              Toast.makeText(ctx, "email is not valid", Toast.LENGTH_SHORT).show()
                          }
                          if (!viewModel.isPasswordValid()) {
                              viewModel.updatePasswordError(true)
                              Toast.makeText(ctx, "Invalid password", Toast.LENGTH_SHORT).show()
                          } else {
                              viewModel.updatePasswordError(false)
                          }
                      } else {
                          viewModel.signUp()
                          Toast.makeText(ctx, "Successfully signed up, please log in", Toast.LENGTH_SHORT).show()
                      }
            }, text = "Sign Up", modifier = Modifier.fillMaxWidth())
    }
}