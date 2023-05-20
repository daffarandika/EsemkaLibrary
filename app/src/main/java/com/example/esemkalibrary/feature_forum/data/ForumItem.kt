package com.example.esemkalibrary.feature_forum.data

import com.example.esemkalibrary.feature_myprofile.data.User
import java.time.LocalDate

data class ForumItem(
    val id: String = "",
    val subject: String = "",
    val createdAt: LocalDate = LocalDate.now(),
    val latestReply: LocalDate = LocalDate.now(),
    val createdBy: User = User(),
)