package com.example.esemkalibrary.feature_signup.data

import android.util.Log
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class ApiService {
    private val conn: HttpURLConnection = URL("$BASE_URL:$PORT/api/users").openConnection() as HttpURLConnection

    init {
        conn.requestMethod = "POST"
        conn.doInput = true
        conn.doOutput = true
        conn.setRequestProperty("Accept", "application/json")
        conn.setRequestProperty("Content-type", "application/json")
    }

    suspend fun signUp(name: String, email: String, password: String) {
        withContext(Dispatchers.IO) {
            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("password", password)
            jsonObject.put("email", email)
            val osw = OutputStreamWriter(conn.outputStream)
            osw.write(jsonObject.toString())
            osw.flush()
            if (conn.responseCode == 200) {
                Log.e("TAG", "signUp: $jsonObject", )
                Log.e("TAG", "signUp: ${conn.inputStream.bufferedReader().readText()}", )
            }
        }
    }
}