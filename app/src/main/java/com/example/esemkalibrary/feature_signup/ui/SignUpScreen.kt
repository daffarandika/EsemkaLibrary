package com.example.esemkalibrary.feature_signup.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryPasswordTextField
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.SandBrown

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    val viewModel: SignUpViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState()
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
        LibraryTextField(value = uiState.value.name,
            onValueChange = {
                            viewModel.updateNameInput(it)
            },
            labelText = "Name",
            isError = uiState.value.isNameError,
            modifier = Modifier.fillMaxWidth())
        LibraryPasswordTextField(onShowPasswordChange = {
                viewModel.updatePasswordVisibility(!uiState.value.isPasswordVisible)
            },
            value = uiState.value.password,
            onValueChange = {viewModel.updatePasswordInput(it)},
            showPassword = uiState.value.isPasswordVisible,
            isError = uiState.value.isPasswordError,
            labelText = "Password",
            modifier = Modifier.fillMaxWidth())
        LibraryPasswordTextField(onShowPasswordChange = { viewModel.updateCPasswordVisibility(!uiState.value.isCPasswordVisible) },
            value = uiState.value.cPassword,
            onValueChange = {viewModel.updateCPasswordInput(it)},
            labelText = "Confirm Password",
            isError = uiState.value.isCPasswordError,
            showPassword = uiState.value.isCPasswordVisible,
            modifier = Modifier.fillMaxWidth())
        LibraryTextField(value = uiState.value.email,
            onValueChange = {viewModel.updateEmailInput(it)},
            labelText = "Email",
            isError = uiState.value.isEmailError,
            modifier = Modifier.fillMaxWidth())
        LibraryButton(
            onClick = {
                      if (!viewModel.isReadyToSignUp()) {
                          if (!viewModel.isEverythingFilled()) {
                              if (uiState.value.name.isBlank()) viewModel.updateNameError(true) else viewModel.updateNameError(false)
                              if (uiState.value.password.isBlank()) viewModel.updatePasswordError(true) else viewModel.updatePasswordError(false)
                              if (uiState.value.cPassword.isBlank()) viewModel.updateCPasswordError(true) else viewModel.updateCPasswordError(false)
                              if (uiState.value.email.isBlank()) viewModel.updateEmailError(true) else viewModel.updateEmailError(false)
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
                          Toast.makeText(ctx, "Success", Toast.LENGTH_SHORT).show()
                      }
            }, text = "Sign Up", modifier = Modifier.fillMaxWidth())
    }
}