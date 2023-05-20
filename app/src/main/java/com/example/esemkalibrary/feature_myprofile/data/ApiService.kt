package com.example.esemkalibrary.feature_myprofile.data

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.processNextEventInCurrentThread
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime


class ApiService {

    suspend fun getImage(token: String): ImageBitmap? {
        if (token.isBlank()) {
            return null
        }
        var bitmap: ImageBitmap?
        try {
            withContext(Dispatchers.IO) {
                val conn = URL("$BASE_URL:$PORT/api/user/me/photo").openConnection() as HttpURLConnection
                conn.setRequestProperty("Authorization", "Bearer $token")
                conn.setRequestProperty("Content-Type", "application/json")
                val inputStream = conn.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream).asImageBitmap()
            }
        } catch (e: java.lang.Exception) {
            bitmap = null
        }
        return bitmap
    }

    fun getUserDetail(token: String): Flow<User> = flow {
        if (token.isBlank()) {
            emit(User())
            return@flow
        }
        val conn = URL("$BASE_URL:$PORT/api/user/me").openConnection() as HttpURLConnection
        conn.setRequestProperty("Authorization", "Bearer $token")
        conn.setRequestProperty("Accept", "application/json")
        conn.setRequestProperty("Content-type", "application/json")

        val inputString = conn.inputStream.bufferedReader().readText()
        val jsonObject = JSONObject(inputString)
        val user = User(
            name = jsonObject.getString("name"),
            email = jsonObject.getString("email"),
            profilePhoto = getImage(token),
        )
        emit(user)
        Log.e("TAG", "getUserDetail: ${conn.responseCode}", )
    }.flowOn(Dispatchers.IO)

    fun getCartHistory(token: String): Flow<List<CartItem>> = flow {
        if (token.isBlank()) {
            emit(emptyList())
            return@flow
        }
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
                        start = LocalDate.parse(jsonObject.getString("start").substring(startIndex = 0, endIndex = 10)),
                        end = LocalDate.parse(jsonObject.getString("end").substring(startIndex = 0, endIndex = 10)),
                        returnedAt = LocalDate.parse(jsonObject.getString("returnedAt").substring(startIndex = 0, endIndex = 10)),
                        bookCount = jsonObject.getInt("bookCount"),
                        status = jsonObject.getString("status"),
                    )
                )
            }
            emit(cartItems)
    }.flowOn(Dispatchers.IO)

}