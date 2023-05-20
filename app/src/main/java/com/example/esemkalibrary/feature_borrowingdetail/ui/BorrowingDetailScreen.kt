package com.example.esemkalibrary.feature_borrowingdetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_borrowingdetail.data.BorrowingDetailUiState
import java.time.format.DateTimeFormatter

@Composable
fun BorrowingDetailScreen(
    modifier: Modifier = Modifier,
    borrowingId: String,
) {
    val viewModel: BorrowingDetailViewModel = viewModel(factory = viewModelFactory {
        BorrowingDetailViewModel(LocalContext.current)
    })
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val token by viewModel.token.collectAsState(initial = "")
    val uiState by viewModel.getUiState(token = token, borrowingId = borrowingId).collectAsState(
        initial = BorrowingDetailUiState())
    LazyColumn(modifier.padding(4.dp), verticalArrangement = Arrangement.spacedBy((8.dp))) {
        item {
            Column(modifier
                .background(color = SandBrown)
                .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "${uiState.start.format(formatter)} - ${uiState.end.format(formatter)}", style = MaterialTheme.typography.h6)
                Text(text = uiState.status)
            }
        }
        items(uiState.books) { book ->
            BorrowingCard(book = book)
        }
    }
}