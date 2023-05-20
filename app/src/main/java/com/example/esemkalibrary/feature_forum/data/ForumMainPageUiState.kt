package com.example.esemkalibrary.feature_forum.data

data class ForumMainPageUiState(
    val posts: List<ForumItem> = emptyList(),
    val currentUserName: String = "",
)
