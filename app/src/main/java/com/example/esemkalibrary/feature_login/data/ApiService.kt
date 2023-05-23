package com.example.esemkalibrary.feature_login.data

import android.util.Log
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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

    fun getToken(email: String, password: String): Flow<String> {
        return flow<String> {
            val outputStreamWriter = OutputStreamWriter(conn.outputStream)
            outputStreamWriter.write("{\n" +
                    "   \"email\": \"$email\",\n" +
                    "   \"password\": \"$password\"\n" +
                    "}")
            Log.e("TAG", "getToken: ${conn.responseCode}", )
            outputStreamWriter.flush()
            val input = conn.inputStream.bufferedReader().readText()
            val jsonObject = JSONObject(input)
            jsonObject.getString("token")
        }.flowOn(Dispatchers.IO)
    }


}