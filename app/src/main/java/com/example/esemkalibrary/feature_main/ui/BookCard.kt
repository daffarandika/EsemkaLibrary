package com.example.esemkalibrary.feature_main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.core.model.BookHeader

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: BookHeader,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(5)
            )
            .padding(4.dp)
            .clickable {
                onClick(book.id)
            }
    ) {
        Image(painterResource(id = R.drawable.default_img),
            "Image of Book",
            modifier = Modifier
                .clip(
                RoundedCornerShape(5))
        )
        Text(book.name)
        Text(book.authors)
    }
}