package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.esemkalibrary.core.components.theme.DirtBrown
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_forum.data.ForumMainPageUiState

@Composable
fun ForumScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: ForumMainPageViewModel = viewModel(factory = viewModelFactory {
        ForumMainPageViewModel(LocalContext.current)
    })
    val token by viewModel.token.collectAsState(initial = "")
    val uiState by viewModel.getUiState(token).collectAsState(initial = ForumMainPageUiState())
    var showDialog by remember{
        mutableStateOf(false)
    }
    var currentThreadId by remember {
        mutableStateOf("")
    }
    if (showDialog) {
        ConfirmationDialog(
            onYesClicked = {
                viewModel.deletePost(token, currentThreadId)
                showDialog = false
            },
            onNoClicked = {
                showDialog = false
            },
            text = "Are you sure you want to delete this post ?"
        )
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(route = Screen.AddThread.route) },
            backgroundColor = DirtBrown,
        ) {
            Text("+")
        }
    },
        backgroundColor = SandBrown
    ) {
        LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(uiState.posts) {
                ForumCard(
                    forumItem = it,
                    modifier = modifier.fillMaxWidth(),
                    onRemoveClicked = {
                        currentThreadId = it.id
                        showDialog = true
                    },
                    onBodyClicked = { navController.navigate(Screen.ThreadDetail.passId(it.id)) },
                    canBeDeleted = (uiState.currentUserName.uppercase() == it.createdBy.name.uppercase())
                )
            }
        }
    }
}