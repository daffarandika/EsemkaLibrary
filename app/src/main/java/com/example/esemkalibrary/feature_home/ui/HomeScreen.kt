package com.example.esemkalibrary.feature_home.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_main.ui.BookCard

@ExperimentalFoundationApi
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val viewModel: HomeViewModel = viewModel(factory = viewModelFactory {
        HomeViewModel(LocalContext.current)
    })
    val uiState by viewModel.uiState.collectAsState()
    val token =  viewModel.token.collectAsState(initial = "").value
    viewModel.updateBooks(viewModel.searchBooks(token = token, query = uiState.searchText).collectAsState(initial = emptyList()).value)
    Column(modifier) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            content = {
                item(span = {
                    GridItemSpan(this.maxCurrentLineSpan)
                }) {
                    Column(modifier.padding(8.dp)) {
                        LibraryTextField(
                            value = uiState.searchText,
                            onValueChange = {
                                viewModel.updateSearchText(it)
                                Log.e("TAG", "HomeScreen: searchedbooks ${uiState.searchText}", )
                            },
                            labelText = "List Books",
                            modifier = Modifier.fillMaxWidth(),
                            hint = { Text("Search") },
                        )
                    }
                }
                items(uiState.books) { book ->
                    BookCard(book = book, onClick = {
                        navController.navigate(Screen.BookDetail.route)
                    })
                }
            },
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )
    }
}