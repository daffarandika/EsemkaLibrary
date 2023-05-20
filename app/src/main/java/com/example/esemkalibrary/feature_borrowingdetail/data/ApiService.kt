package com.example.esemkalibrary.feature_borrowingdetail.data

import android.util.Log
import androidx.compose.ui.platform.LocalDensity
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.core.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate

class ApiService {
    fun getUiState(token: String, borrowingId: String): Flow<BorrowingDetailUiState> = flow{
        if (token.isBlank()) {
            emit(BorrowingDetailUiState())
            Log.e("TAG", "getUiState: empty token", )
            return@flow
        }
        try {
            val conn = URL("$BASE_URL:$PORT/api/borrowing/$borrowingId").openConnection() as HttpURLConnection
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Content-Type", "application/json")
            conn.setRequestProperty("Accept", "application/json")
            Log.e("TAG", "getUiState: ${conn.responseCode}", )

            val inputString = conn.inputStream.bufferedReader().readText()
            Log.e("TAG", "getUiState: ${inputString}", )
            val jsonObject = JSONObject(inputString)
            val start = LocalDate.parse(jsonObject.getString("start").substring(0, 10))
            val end = LocalDate.parse(jsonObject.getString("end").substring(0, 10))
            val status = jsonObject.getString("status")
            val books: MutableList<Book> = mutableListOf()
            val bookBorrowings = jsonObject.getJSONArray("bookBorrowings")
            for (i in 0 until bookBorrowings.length()) {
                val bookBorrowingId = bookBorrowings.getJSONObject(i)
                val book = bookBorrowingId.getJSONObject("book")
                books.add(Book(
                    id = book.getString("id"),
                    name = book.getString("name"),
                    authors = book.getString("authors"),
                    isbn = book.getString("isbn"),
                    publisher = book.getString("publisher"),
                    available = book.getInt("available"),
                    description = book.getString("description"),
                ))
            }
            emit(BorrowingDetailUiState(
                start = start,
                end = end,
                status = status,
                books = books
            ))
        } catch (e: java.lang.Exception) {
            emit(BorrowingDetailUiState())
            Log.e("TAG", "getUiState: ${e.message.toString()}", )
            return@flow
        }
    }.flowOn(Dispatchers.IO)
}