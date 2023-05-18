package com.example.esemkalibrary.feature_home.ui

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
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.model.BookHeader

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    book: BookHeader,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .height(250.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(5)
            )
            .clickable {
                onClick(book.id)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = if (book.image == null) painterResource(id = R.drawable.no_image) else BitmapPainter(image = book.image),
            "Image of Book",
            modifier = Modifier
                .size(height = 200.dp, width = 200.dp)
                .clip(
                RoundedCornerShape(5))
        )
        Column(Modifier.fillMaxWidth()){
            Text(text = book.name, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.fillMaxWidth())
            Text(text = book.authors, textAlign = TextAlign.Center, fontSize = 12.sp, overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview
@Composable
fun BCPrev() {
    BookCard(book = BookHeader("9", "The Pirates of the Carrabian", "idk"), onClick = {})
}