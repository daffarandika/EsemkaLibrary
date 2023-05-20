package com.example.esemkalibrary.feature_forum.data

import com.example.esemkalibrary.feature_myprofile.data.User
import java.time.LocalDate

data class Reply(
    val id: String = "",
    val message: String = "",
    val createdAt: LocalDate = LocalDate.now(),
    val createdBy: User = User()
)
