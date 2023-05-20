package com.example.esemkalibrary.feature_bookdetail.data

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ApiService {

    private suspend fun getImage(token: String, id: String): ImageBitmap? {
        if (token.isEmpty()) {
            return null
        }
        var bitmap: ImageBitmap?
        try {
            withContext(Dispatchers.IO) {
                val conn = URL("$BASE_URL:$PORT/Api/Book/$id/photo").openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                conn.setRequestProperty("Authorization", "Bearer $token")
                conn.setRequestProperty("Content-Type", "application/json")
                conn.setRequestProperty("Accept", "application/json")

                val inputStream = conn.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream).asImageBitmap()
            }
        } catch (e: java.lang.Exception) {
            bitmap = null
        }
        return bitmap
    }

    fun getData(token: String, bookId: String): Flow<BookDetailUiState> = flow {
        if (token.isBlank()) {
            emit(BookDetailUiState())
            return@flow
        }
        val conn = URL("$BASE_URL:$PORT/api/book/$bookId").openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Authorization", "Bearer $token")
        conn.setRequestProperty("Content-Type", "application/json")
        val inputString = conn.inputStream.bufferedReader().readText()
        val jsonObject = JSONObject(inputString)
        emit(BookDetailUiState(
            id = jsonObject.getString("id"),
            name = jsonObject.getString("name"),
            authors = jsonObject.getString("authors"),
            isbn = jsonObject.getString("isbn"),
            publisher = jsonObject.getString("publisher"),
            available = jsonObject.getInt("available"),
            description = jsonObject.getString("description"),
            image = getImage(token, bookId)
        ))
    }.flowOn(Dispatchers.IO)


}