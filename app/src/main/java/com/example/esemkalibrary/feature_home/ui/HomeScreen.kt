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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.model.Book
import com.example.esemkalibrary.feature_bookdetail.ui.BookDetailScreen
import com.example.esemkalibrary.feature_main.ui.BookCard

@ExperimentalFoundationApi
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Column(modifier) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    content = {
                        item(span = {
                            GridItemSpan(this.maxCurrentLineSpan)
                        }) {
                            Column(modifier.padding(8.dp)) {
                                LibraryTextField(value = "", onValueChange = {}, labelText = "Search", modifier = Modifier.fillMaxWidth())
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
                            BookCard(book = book, onClick = {
                                navController.navigate("bookdetail")
                            })
                        }
                    },
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                )
            }
        }
        composable("bookdetail") {
            BookDetailScreen(id = "9")
        }
    }
}