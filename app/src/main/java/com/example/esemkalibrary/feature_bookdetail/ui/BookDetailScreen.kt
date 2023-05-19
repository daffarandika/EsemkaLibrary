package com.example.esemkalibrary.feature_bookdetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.theme.DirtBrown
import com.example.esemkalibrary.core.components.theme.MudBrown
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_bookdetail.data.BookDetailUiState

@Composable
fun BookDetailScreen(modifier: Modifier = Modifier, bookId: String) {
    val viewModel: BookDetailViewModel = viewModel(factory = viewModelFactory {
        BookDetailViewModel(LocalContext.current)
    })
    val token = viewModel.token.collectAsState(initial = "")
    val uiState = viewModel.getData(token = token.value, bookId = bookId).collectAsState(initial = BookDetailUiState())
    if (uiState.value.name.isBlank()) {
        CircularProgressIndicator(Modifier.fillMaxSize())
    }
    Scaffold(
        modifier = modifier,
        backgroundColor = SandBrown,
        floatingActionButton = {
            FloatingActionButton(onClick = {
               viewModel.addToCart(uiState.value.id)
            },
                backgroundColor = DirtBrown,
                shape = RoundedCornerShape(5),
                modifier = Modifier
                    .fillMaxWidth(0.91f)
                    .padding(4.dp)
                ,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 4.dp
                )
            ) {
                Text("Add to Cart")
            }
    }) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(painter = if (uiState.value.image == null) painterResource(id = R.drawable.no_image) else BitmapPainter(uiState.value.image!!),
                "Book Image",
                modifier = Modifier.size(256.dp))
            Text(
                text = uiState.value.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(uiState.value.authors)
            Row(modifier.align(Alignment.Start)) {
                Text("ISBN-10: ", fontWeight = FontWeight.Bold)
                Text(uiState.value.isbn)
            }
            Row(modifier.align(Alignment.Start)) {
                Text("Publisher: ", fontWeight = FontWeight.Bold)
                Text(uiState.value.publisher)
            }
            Row(modifier.align(Alignment.Start)) {
                Text("Available: ", fontWeight = FontWeight.Bold)
                Text(uiState.value.available.toString())
            }
            Text(uiState.value.description)
            Spacer(modifier.size(64.dp))
        }
    }
}