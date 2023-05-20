package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.utils.viewModelFactory

@Composable
fun AddThreadScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: AddThreadViewModel = viewModel(factory = viewModelFactory {
        AddThreadViewModel(LocalContext.current)
    })
    val uiState by viewModel.uiState.collectAsState()
    val token by viewModel.token.collectAsState(initial = "")
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SandBrown)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LibraryTextField(value = uiState.subject, onValueChange = {
              viewModel.updateSubject(it)
        }, labelText = "Subject", modifier = modifier.fillMaxWidth())
        LibraryTextField(value = uiState.body, onValueChange = {
              viewModel.updateBody(it)
        }, modifier = Modifier
            .height(256.dp)
            .fillMaxWidth(),
            labelText = "Body"
        )
        LibraryButton(
            onClick = {
                viewModel.addThread(token = token,
                    subject = uiState.subject,
                    body = uiState.body)
                navController.popBackStack()
            },
            text = "Add thread",
            modifier = Modifier.fillMaxWidth()
        )
    }
}