package com.example.esemkalibrary.feature_home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.feature_main.ui.BookCard

@ExperimentalFoundationApi
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onBookClicked: (String) -> Unit) {
    Column(modifier) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            content = {
                item(span = {
                    GridItemSpan(this.maxCurrentLineSpan)
                }) {
                    Column(modifier.padding(8.dp)) {
                        LibraryTextField(value = "", onValueChange = {}, labelText = "Search")
                    }
                }
                items(listOf(
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
                    BookCard(book = book, onClick = onBookClicked)
                }
            },
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )
    }
}