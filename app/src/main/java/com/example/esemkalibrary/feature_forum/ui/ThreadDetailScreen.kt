package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.Grey
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_forum.data.ForumDetailUiState

@Composable
fun ThreadDetailScreen(modifier: Modifier = Modifier, threadId: String = "ec16f7c8-7b68-4142-a61a-298e2db5bb9d") {
    val viewModel: ThreadDetailViewModel = viewModel(factory = viewModelFactory {
        ThreadDetailViewModel(LocalContext.current)
    })
    val token = viewModel.token.collectAsState(initial = "")
    val uiState = viewModel.getThreadDetail(token.value, threadId).collectAsState(initial = ForumDetailUiState())
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn(modifier
            .weight(weight = 1f, fill = false)
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            item {
                PostCard(post = uiState.value.mainPost)
            }
            items(uiState.value.mainPost.replies) {reply ->
                ReplyCard(reply = reply)
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(width = 1.dp, color = Grey.copy(0.5f)))
                .background(color = White)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            LibraryTextField(value = "", onValueChange = {}, hint = { Text("Reply....") }, modifier = Modifier.fillMaxWidth(), showLabel = false)
            LibraryButton(onClick = { /*TODO*/ }, text = "Add Reply", modifier = Modifier.fillMaxWidth())
        }
    }
}