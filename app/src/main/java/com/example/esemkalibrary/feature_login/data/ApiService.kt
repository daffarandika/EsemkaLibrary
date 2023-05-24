package com.example.esemkalibrary.feature_login.data

import android.util.Log
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
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

    suspend fun getToken(email: String, password: String): Flow<String> {
        return flow {
            val outputStreamWriter = OutputStreamWriter(conn.outputStream)
            outputStreamWriter.write("{\n" +
                    "   \"email\": \"$email\",\n" +
                    "   \"password\": \"$password\"\n" +
                    "}")
            outputStreamWriter.flush()
            outputStreamWriter.close()
            Log.e("TAG", "getToken: response code ${conn.responseCode}", )
            if (conn.responseCode != 200) {
                throw Exception("Invalid email or password")
            }
            val input = conn.inputStream.bufferedReader().readText()
            val jsonObject = JSONObject(input)
            emit(jsonObject.getString("token"))
            conn.disconnect()
        }
            .flowOn(Dispatchers.IO)
    }

}