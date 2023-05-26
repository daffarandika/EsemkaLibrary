package com.example.esemkalibrary.feature_myprofile.data

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate


class ApiService {

    fun getImage(token: String): Flow<ImageBitmap> = flow {
        if (token.isBlank()) {
            throw Exception("Token is blank")
        }

        val conn = URL("$BASE_URL:$PORT/api/user/me/photo").openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Authorization", "Bearer $token")
        try {
            val inputStream = conn.inputStream
            val bitmap = withContext(Dispatchers.IO){
                BitmapFactory.decodeStream(inputStream).asImageBitmap()
            }
            emit(bitmap)
        } catch (e: CancellationException){
            Log.e("TAG", "getImage: ${e.message}", )
        } finally {
            conn.disconnect()
        }

    }.flowOn(Dispatchers.IO)

    fun getUserDetail(token: String, imageBitmap: ImageBitmap): Flow<User> = flow {
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
            profilePhoto = imageBitmap,
        )
        emit(user)
        Log.e("TAG", "getUserDetail: $user", )
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