package com.example.esemkalibrary.feature_mycart.data

import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.core.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ApiService {
    fun getBooksFromId(token: String, bookIds: List<String>): Flow<List<Book>> = flow {
        if (token.isBlank()) {
            emit(emptyList())
            return@flow
        }
        val books = mutableListOf<Book>()
        bookIds.forEach { bookId ->
            val conn = URL("$BASE_URL:$PORT/api/book/${bookId}").openConnection() as HttpURLConnection
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Content-Type", "application/json")

            val inputString = conn.inputStream.bufferedReader().readText()
            val jsonObject = JSONObject(inputString)
            books.add(Book(
                id = jsonObject.getString("id"),
                name = jsonObject.getString("name"),
                authors = jsonObject.getString("authors"),
                isbn = jsonObject.getString("isbn"),
                publisher = jsonObject.getString("publisher"),
                available = jsonObject.getInt("available"),
                description = jsonObject.getString("description"),
                ))
        }
        emit(books)
    }
}