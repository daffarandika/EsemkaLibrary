package com.example.esemkalibrary.feature_forum.data

data class ThreadDetailUiState(
    val mainPost: MainPost = MainPost(),
    val currentUsername: String = "",
    val replyText: String= "",
)
