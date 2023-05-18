package com.example.esemkalibrary.feature_home.data

import com.example.esemkalibrary.core.model.BookHeader

data class HomeUiState(
    val books: List<BookHeader> = emptyList(),
    val searchText: String = ""
)
