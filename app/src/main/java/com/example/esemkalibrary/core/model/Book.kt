package com.example.esemkalibrary.core.model

data class Book(
    val id: String,
    val name: String,
    val authors: String,
    val isbn: String,
    val publisher: String,
    val available: Int,
    val description: String
)
