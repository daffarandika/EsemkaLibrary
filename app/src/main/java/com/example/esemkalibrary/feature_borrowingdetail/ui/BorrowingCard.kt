package com.example.esemkalibrary.feature_borrowingdetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.model.Book

@Composable
fun BorrowingCard(modifier: Modifier = Modifier, book: Book) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painterResource(id = R.drawable.default_img),
            contentDescription = "Book Image",
            modifier = Modifier.size(128.dp)
        )
        Column(modifier.fillMaxWidth().height(128.dp), verticalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = book.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = book.authors
            )
            Row(Modifier) {
                Text( text = "ISBN-10: ", fontWeight = FontWeight.Bold )
                Text( text = book.isbn)
            }
        }
    }
}