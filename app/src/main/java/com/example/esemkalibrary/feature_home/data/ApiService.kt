package com.example.esemkalibrary.feature_home.data

import android.content.Context
import android.graphics.BitmapFactory
import android.media.session.MediaSession.Token
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.data.LocalStorage.Companion.TOKEN_KEY
import com.example.esemkalibrary.core.data.LocalStorage.Companion.dataStore
import com.example.esemkalibrary.core.model.BookHeader
import com.example.esemkalibrary.core.model.Output
import com.example.esemkalibrary.core.model.TokenExpiredException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiService(
    val context: Context,
) {


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

    fun searchBooks(token: String, query: String?): Flow<List<BookHeader>> = flow {
        Log.e("TAG", "request was made searchBooks: $token", )
        if (token.isEmpty()) {
            throw TokenExpiredException("Token has expired")
        }
        val conn = URL(if (query.isNullOrEmpty()) "$BASE_URL:$PORT/Api/Book" else "$BASE_URL:$PORT/Api/Book?searchText=$query").openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Authorization", "Bearer $token")
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")

        if (conn.responseCode == HttpURLConnection.HTTP_OK) {
            val inputStringBuilder = StringBuilder()
            val responseReader = BufferedReader(InputStreamReader(conn.inputStream))

            var line: String?
            while (responseReader.readLine().also { line = it } != null) {
                inputStringBuilder.append(line)
            }
            responseReader.close()
            val inputString = inputStringBuilder.toString()
            val jsonArray = JSONArray(inputString)
            val books = mutableListOf<BookHeader>()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                books.add(BookHeader(
                    id = jsonObject.getString("id"),
                    name = jsonObject.getString("name"),
                    authors = jsonObject.getString("authors"),
                    image = getImage(token = token, id = jsonObject.getString("id"))
                ))
            }
            emit(books)
        } else {
            if (conn.responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                throw TokenExpiredException("Token has expired")
            } else {
                val errorStream = conn.errorStream
                val responseBody = errorStream.bufferedReader().use {
                    it.readText()
                }
                errorStream.close()
                throw Exception(responseBody)
            }
        }

    }.flowOn(Dispatchers.IO)
}