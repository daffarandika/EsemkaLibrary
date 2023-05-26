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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.esemkalibrary.core.components.LibraryTextField
import com.example.esemkalibrary.core.components.theme.MudBrown
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.model.BookHeader
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_home.data.HomeUiState
import kotlinx.coroutines.flow.collect

@ExperimentalFoundationApi
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController, homeViewModel: HomeViewModel) {

    val homeUiState by homeViewModel.homeState.collectAsState()
    val uiState by homeViewModel.uiState.collectAsState()

    val books = homeUiState.data
    if (books == null || books.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(16.dp), color = MudBrown)
    } else {
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
                                },
                                labelText = "List Books",
                                modifier = Modifier.fillMaxWidth(),
                                hint = { Text("Search") },
                            )
                        }
                    }
                    items(books ?: emptyList()) { book ->
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
@ExperimentalFoundationApi
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val homeViewModel: HomeViewModel = viewModel(factory = viewModelFactory {
        HomeViewModel(LocalContext.current)
    })

    HomeScreen(navController = navController, homeViewModel = homeViewModel)
}