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
import androidx.compose.ui.unit.dp
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.Grey
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.model.Book

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCartScreen(modifier: Modifier = Modifier) {
    BottomSheetScaffold(
        backgroundColor = SandBrown,
        scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        ),
        sheetShape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8),
        sheetContent = {
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp)
                    .padding(horizontal = 16.dp)
                ,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Grey.copy(alpha = 0.2f), shape = RoundedCornerShape(50))
                        .width(64.dp)
                        .height(4.dp)
                )
                Spacer(modifier.size(25.dp))
                Column(Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.5f)
                ) {
                    Text("Date Borrow:")
                    Row(modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        LibraryTextField(value = "21 Sept 2022", onValueChange = {}, showLabel = false, modifier = Modifier.weight(1f))
                        Text("-")
                        LibraryTextField(value = "21 Sept 2022", onValueChange = {}, showLabel = false, modifier = Modifier.weight(1f))
                    }
                    LibraryButton(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth(), text = "Booking Borrow")
                }
            }
        }
    ) {
        LazyColumn(modifier.fillMaxSize()) {
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
            item {
                Spacer(modifier.size(64.dp))
            }
        }
    }
}