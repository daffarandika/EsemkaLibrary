package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.feature_mycart.ui.CartCard

@Composable
fun ForumScreen(modifier: Modifier = Modifier) {
    CartCard(book =
        Book(
            id = "12",
            name = "database design",
            authors = "uncle bob",
            isbn = "21441-421",
            publisher = "7 Seas",
            available = 12,
            description = "A book"
        ),
    )
}