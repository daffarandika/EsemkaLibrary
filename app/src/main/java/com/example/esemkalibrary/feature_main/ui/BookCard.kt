package com.example.esemkalibrary.feature_main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .padding(8.dp)
            .height(300.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(5)
            )
            .clickable {
                onClick(book.id)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = R.drawable.default_img),
            "Image of Book",
            modifier = Modifier
                .clip(
                RoundedCornerShape(5))
        )
        Text(text = book.name, textAlign = TextAlign.Center, fontSize = 18.sp, overflow = TextOverflow.Ellipsis, maxLines = 2)
        Text(text = book.authors, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis, maxLines = 1)
    }
}

@Preview
@Composable
fun BCPrev() {
    BookCard(book = BookHeader("9", "The Pirates of the Carrabian", "idk"), onClick = {})
}