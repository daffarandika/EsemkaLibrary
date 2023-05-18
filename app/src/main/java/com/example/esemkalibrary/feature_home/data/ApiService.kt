package com.example.esemkalibrary.feature_home.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.data.LocalStorage.Companion.TOKEN_KEY
import com.example.esemkalibrary.core.data.LocalStorage.Companion.dataStore
import com.example.esemkalibrary.core.model.BookHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class ApiService(
    val context: Context,
) {


    fun getBooks(token: String): Flow<List<BookHeader>> = flow{
        if (token.isEmpty()) {
            emit(emptyList())
            return@flow
        }
        val conn = URL("$BASE_URL:$PORT/Api/Book").openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Authorization", "Bearer $token")
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")

        val inputString = conn.inputStream.bufferedReader().readText()
        val jsonArray = JSONArray(inputString)
        val books = mutableListOf<BookHeader>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            books.add(BookHeader(
                id = jsonObject.getString("id"),
                name = jsonObject.getString("name"),
                authors = jsonObject.getString("authors"),
            ))
        }
        emit(books)
    }.flowOn(Dispatchers.IO)

    fun searchBooks(token: String, query: String?): Flow<List<BookHeader>> = flow {
        if (token.isEmpty()) {
            emit(emptyList())
            return@flow
        }
        val conn = URL(if (query.isNullOrEmpty()) "$BASE_URL:$PORT/Api/Book" else "$BASE_URL:$PORT/Api/Book?searchText=$query").openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Authorization", "Bearer $token")
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")

        val inputString = conn.inputStream.bufferedReader().readText()
        val jsonArray = JSONArray(inputString)
        val books = mutableListOf<BookHeader>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            books.add(BookHeader(
                id = jsonObject.getString("id"),
                name = jsonObject.getString("name"),
                authors = jsonObject.getString("authors"),
            ))
        }
        emit(books)
        Log.e("TAG", "searchBooks: $books", )
    }.flowOn(Dispatchers.IO)
}