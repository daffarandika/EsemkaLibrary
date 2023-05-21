package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.Grey
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_forum.data.ThreadDetailUiState

@Composable
fun ThreadDetailScreen(modifier: Modifier = Modifier, threadId: String) {
    val viewModel: ThreadDetailViewModel = viewModel(factory = viewModelFactory {
        ThreadDetailViewModel(LocalContext.current)
    })
    val token by viewModel.token.collectAsState(initial = "")
    val mainPost = viewModel.getThreadDetail(token, threadId).collectAsState(initial = ThreadDetailUiState()).value.mainPost
    val currentUsername = viewModel.getThreadDetail(token, threadId).collectAsState(initial = ThreadDetailUiState()).value.currentUsername
    val uiState by viewModel.uiState.collectAsState()
    var replyId by remember {
        mutableStateOf("")
    }
    if (uiState.showConfirmationDialog) {
        ConfirmationDialog(
            onYesClicked = {
                viewModel.deleteReply(token, threadId, replyId)
                viewModel.updateDialogVisibility(false)
            },
            onNoClicked = {
                viewModel.updateDialogVisibility(false)
            },
            text = "Are you sure you want to delete this reply"
        )
    }
    viewModel.updateMainPost(mainPost)
    viewModel.updateCurrentUsername(currentUsername)
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(modifier
            .weight(weight = 1f, fill = false)
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            item {
                PostCard(post = uiState.mainPost)
            }
            items(uiState.mainPost.replies) {reply ->
                ReplyCard(
                    reply = reply,
                    canBeDeleted = (uiState.currentUsername.equals(reply.createdBy.name, true)),
                    onDeleteClicked = {
                        replyId = reply.id
                        viewModel.updateDialogVisibility(true)
                    }
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(width = 1.dp, color = Grey.copy(0.5f)))
                .background(color = White)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            LibraryTextField(value = uiState.replyText, onValueChange = {
                 viewModel.updateReplyText(it)
            }, hint = { Text("Reply....") }, modifier = Modifier.fillMaxWidth(), showLabel = false)
            LibraryButton(onClick = {
                viewModel.postReply(token, threadId, uiState.replyText)
                viewModel.updateReplyText("")
            }, text = "Add Reply", modifier = Modifier.fillMaxWidth())
        }
    }
}