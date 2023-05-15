package com.example.esemkalibrary.feature_borrowingdetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.model.Book

@Composable
fun BorrowingDetailScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier.padding(4.dp), verticalArrangement = Arrangement.spacedBy((8.dp))) {
        item {
            Column(modifier
                .background(color = SandBrown)
                .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "13 Sept 2023 - 21 Sept 2023", fontSize = 18.sp)
                Text(text = "Borrowing")
            }
        }
        items(
            listOf(
                Book(
                    id = "12",
                    name = "database design",
                    authors = "uncle bob",
                    isbn = "21441-421",
                    publisher = "7 Seas",
                    available = 12,
                    description = "A book"
                ),
                Book(
                    id = "12",
                    name = "database design",
                    authors = "uncle bob",
                    isbn = "21441-421",
                    publisher = "7 Seas",
                    available = 12,
                    description = "A book"
                ),
                Book(
                    id = "12",
                    name = "database design",
                    authors = "uncle bob",
                    isbn = "21441-421",
                    publisher = "7 Seas",
                    available = 12,
                    description = "A book"
                ),
                Book(
                    id = "12",
                    name = "database design",
                    authors = "uncle bob",
                    isbn = "21441-421",
                    publisher = "7 Seas",
                    available = 12,
                    description = "A book"
                ),
                Book(
                    id = "12",
                    name = "database design",
                    authors = "uncle bob",
                    isbn = "21441-421",
                    publisher = "7 Seas",
                    available = 12,
                    description = "A book"
                ),
        )) { book ->
            BorrowingCard(book = book)
        }
    }
}