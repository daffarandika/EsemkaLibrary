package com.example.esemkalibrary.feature_mycart.data

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.core.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ApiService {
    private suspend fun getBookImage(token: String, bookId: String): ImageBitmap? {
        if (token.isBlank()) {
            return null
        }
        var bitmap: ImageBitmap? = null
        withContext(Dispatchers.IO) {
            bitmap = try {
                val conn = URL("$BASE_URL:$PORT/api/book/$bookId/photo").openConnection() as HttpURLConnection
                conn.setRequestProperty("Authorization", "Bearer $token")
                conn.setRequestProperty("Content-Type", "application/json")
                val inputStream = conn.inputStream
                BitmapFactory.decodeStream(inputStream).asImageBitmap()
            } catch (e: Exception) {
                null
            }
        }
        return bitmap
    }
    fun getBooksFromId(token: String, bookIds: List<String>): Flow<List<Book>> = flow {
        if (token.isBlank()) {
            emit(emptyList())
            return@flow
        }
        val books = mutableListOf<Book>()
        bookIds.forEach { bookId ->
            if (bookId.isBlank()) {
                emit(emptyList())
                return@flow
            }
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
                image = getBookImage(token, jsonObject.getString("id"))
            ))
        }
        emit(books)
    }.flowOn(Dispatchers.IO)
}