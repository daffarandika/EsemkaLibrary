package com.example.esemkalibrary.feature_myprofile.data

import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime


class ApiService {

    suspend fun getCartHistory(token: String): Flow<List<CartItem>> = flow {
        if (token.isBlank()) {
            emit(emptyList())
            return@flow
        }
        withContext(Dispatchers.IO) {
            val conn = URL("$BASE_URL:$PORT/api/borrowing").openConnection() as HttpURLConnection
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Content-type", "application/json")

            val inputString = conn.inputStream.bufferedReader().readText()
            val jsonArray = JSONArray(inputString)
            val cartItems = mutableListOf<CartItem>()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                cartItems.add(
                    CartItem(
                        id = jsonObject.getString("id"),
                        start = LocalDateTime.parse(jsonObject.getString("start")),
                        end = LocalDateTime.parse(jsonObject.getString("end")),
                        returnedAt = LocalDateTime.parse(jsonObject.getString("returnedAt")),
                        bookCount = jsonObject.getInt("bookCount"),
                        status = jsonObject.getString("status"),
                    )
                )
            }
            emit(cartItems)
        }
    }

}