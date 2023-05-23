package com.example.esemkalibrary.feature_home.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.model.BookHeader
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.utils.viewModelFactory

@ExperimentalFoundationApi
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: HomeViewModel = viewModel(factory = viewModelFactory {
        HomeViewModel(LocalContext.current)
    })
    val uiState by viewModel.uiState.collectAsState()
    val token =  viewModel.token.collectAsState(initial = "").value
//    if (uiState.searchText.isBlank()) {
//        viewModel.updateBooks(viewModel.searchBooks(token = token, query = "").collectAsState(initial = emptyList()).value)
//    }
    viewModel.searchBooks(token = token, query = "")
    when (uiState) {
        is com.example.esemkalibrary.feature_login.data.Result.Loading  -> {
            CircularProgressIndicator()
        }
        is com.example.esemkalibrary.feature_login.data.Result.Error -> {
            Toast.makeText(LocalContext.current, "uiS", Toast.LENGTH_SHORT).show()
        }
        is com.example.esemkalibrary.feature_login.data.Result.Success -> {
            Column(modifier) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    content = {
                        item(span = {
                            GridItemSpan(this.maxCurrentLineSpan)

                        }) {
                            Column(modifier.padding(8.dp)) {
                                LibraryTextField(
                                    value = "",
                                    onValueChange = {
        //                                viewModel.updateSearchText(it)
        //                                Log.e("TAG", "HomeScreen: searchedbooks ${uiState.searchText}", )
                                    },
                                    labelText = "List Books",
                                    modifier = Modifier.fillMaxWidth(),
                                    hint = { Text("Search") },
                                )
                            }
                        }
                        val books : List<BookHeader> = (uiState as com.example.esemkalibrary.feature_login.data.Result.Success).data
                        items(books) { book ->
                            BookCard(book = book, onClick = {
                                navController.navigate(Screen.BookDetail.passBookId(bookId = book.id))
                            })
                        }
                    },
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                )
            }
        }
    }
}