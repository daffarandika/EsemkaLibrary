package com.example.esemkalibrary.feature_login.data

import android.util.Log
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.core.model.Output
import com.example.esemkalibrary.core.model.TokenExpiredException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class ApiService {

    private val conn: HttpURLConnection = URL("$BASE_URL:$PORT/Api/Auth").openConnection() as HttpURLConnection

    init {
        conn.requestMethod = "POST"
        conn.doOutput = true
        conn.doInput = true
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")
    }

    fun getToken(email: String, password: String): Flow<Pair<String, String>> {
        return flow {

            val requestBody = JSONObject()
            requestBody.put("email", email)
            requestBody.put("password", password)

            conn.outputStream.use {
                it.write(requestBody.toString().toByteArray())
            }
            val responseCode = conn.responseCode

            val response = if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = conn.inputStream
                val responseBody = inputStream.bufferedReader().use {
                    it.readText()
                }
                inputStream.close()

                val jsonObject = JSONObject(responseBody)
                val token = jsonObject.getString("token")
                val expiration = jsonObject.getString("expired")
                Pair<String, String>(token, expiration)
            } else {
                val errorStream = conn.errorStream
                val responseBody = errorStream.bufferedReader().use {
                    it.readText()
                }
                errorStream.close()
                throw Exception(responseBody)
            }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

}