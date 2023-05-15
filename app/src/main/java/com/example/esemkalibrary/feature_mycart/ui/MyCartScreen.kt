package com.example.esemkalibrary.feature_mycart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.Grey
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.model.Book

@Composable
fun MyCartScreen(modifier: Modifier = Modifier) {
    Column {
        LazyColumn(modifier = modifier.weight(1f, fill = false)) {
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
                )
            ) { book ->
                CartCard(book = book)
            }
        }
        Column(
            modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
            ,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Date Borrow:",modifier = Modifier.align(Alignment.Start))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    ,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically) {
                    LibraryTextField(value = "21 Sept 2022", onValueChange = {}, showLabel = false, modifier = Modifier
                        .width(165.dp)
                    )
                    Text("-")
                    LibraryTextField(value = "21 Sept 2022", onValueChange = {}, showLabel = false, modifier = Modifier
                        .width(165.dp)
                    )
                }
                LibraryButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(), text = "Booking Borrow")
            }
        }
    }
}

@Preview
@Composable
fun CartScreenPrev() {
    MyCartScreen(modifier = Modifier.fillMaxSize())
}