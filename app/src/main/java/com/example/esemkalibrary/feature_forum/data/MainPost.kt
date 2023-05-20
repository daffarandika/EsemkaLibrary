package com.example.esemkalibrary.feature_forum.data

import com.example.esemkalibrary.feature_myprofile.data.User
import java.time.LocalDate

data class MainPost(
    val subject: String = "",
    val body: String = "",
    val createdAt: LocalDate = LocalDate.now(),
    val createdBy: User = User(),
    val replies: List<Reply> = emptyList()
)
