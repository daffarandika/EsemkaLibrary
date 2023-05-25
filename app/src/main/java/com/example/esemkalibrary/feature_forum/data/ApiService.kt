package com.example.esemkalibrary.feature_forum.data

import android.util.Log
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.feature_myprofile.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate

class ApiService {

    fun getMainUiState(token: String): Flow<ForumMainPageUiState> = flow {
        if (token.isBlank()) {
            emit(ForumMainPageUiState())
            return@flow
        }
        val conn = URL("$BASE_URL:$PORT/api/forum").openConnection() as HttpURLConnection
        conn.setRequestProperty("Authorization", "Bearer $token")
        conn.setRequestProperty("Content-type", "application/json")

        val inputString = conn.inputStream.bufferedReader().readText()
        val jsonArray = JSONArray(inputString)
        val posts: MutableList<ForumItem> = mutableListOf()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val user = jsonObject.getJSONObject("createdBy")
            posts.add(
                ForumItem(
                    id = jsonObject.getString("id"),
                    subject = jsonObject.getString("subject"),
                    createdAt = LocalDate.parse(jsonObject.getString("createdAt").substring(0, 10)),
                    latestReply = LocalDate.parse(jsonObject.getString("lastestReply").substring(0, 10)),
                    createdBy = User(
                        name = user.getString("name"),
                        email = user.getString("email"),
                    )
                )
            )
        }
        val conn2 = URL("$BASE_URL:$PORT/api/user/me").openConnection() as HttpURLConnection
        conn2.setRequestProperty("Authorization", "Bearer $token")
        conn2.setRequestProperty("Content-type", "application/json")

        val inputString2 = conn2.inputStream.bufferedReader().readText()
        val jsonObject = JSONObject(inputString2)
        val username = jsonObject.getString("name")
        emit(ForumMainPageUiState(posts, username))
    }.flowOn(Dispatchers.IO)

    fun getThreadDetail(token: String, threadId: String): Flow<ThreadDetailUiState> = flow {
        if (token.isBlank()) {
            emit(ThreadDetailUiState())
            return@flow
        }
        val conn = URL("$BASE_URL:$PORT/api/forum/$threadId").openConnection() as HttpURLConnection
        conn.setRequestProperty("Authorization", "Bearer $token")
        conn.setRequestProperty("Content-type", "application/json")

        val inputString = conn.inputStream.bufferedReader().readText()
        Log.e("TAG", "getThreadDetail: $inputString")
        val jsonObject = JSONObject(inputString)
        val jsonArray = jsonObject.getJSONArray("replies")
        val replies = mutableListOf<Reply>()
        for (i  in 0 until jsonArray.length()) {
            val reply = jsonArray.getJSONObject(i)
            replies.add(Reply(
                id = reply.getString("id"),
                message = reply.getString("message"),
                createdAt = LocalDate.parse(reply.getString("createdAt").substring(0,10)),
                createdBy = User(
                    name = reply.getJSONObject("createdBy").getString("name"),
                    email = reply.getJSONObject("createdBy").getString("email"),
                )
            ))
        }
        val conn2 = URL("$BASE_URL:$PORT/api/user/me").openConnection() as HttpURLConnection
        conn2.setRequestProperty("Authorization", "Bearer $token")
        conn2.setRequestProperty("Content-type", "application/json")

        val inputString2 = conn2.inputStream.bufferedReader().readText()
        val jsonObject2 = JSONObject(inputString2)
        val username = jsonObject2.getString("name")
        emit(ThreadDetailUiState(
            MainPost(
                subject = jsonObject.getString("subject"),
                body = jsonObject.getString("body"),
                createdAt = LocalDate.parse(jsonObject.getString("createdAt").substring(0,10)),
                createdBy = User(
                    name = jsonObject.getJSONObject("createdBy").getString("name"),
                    email = jsonObject.getJSONObject("createdBy").getString("email"),
                ),
                replies = replies,
            ),
            username
        ))
    }.flowOn(Dispatchers.IO)

    suspend fun addThread(token: String, subject: String, body: String) {
        if (token.isBlank()) return
        withContext(Dispatchers.IO) {
            val conn = URL("$BASE_URL:$PORT/api/forum").openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Accept", "text/plain")
            conn.setRequestProperty("Content-type", "application/json")
            conn.doOutput = true

            val requestBody = JSONObject().apply {
                put("subject", subject)
                put("body", body)
            }.toString()
            val osw = OutputStreamWriter(conn.outputStream)
            osw.write(requestBody)
            osw.flush()
            val inputString = conn.inputStream.bufferedReader().readText()
            Log.e("TAG", "addThread: $inputString", )
        }
    }
    suspend fun addReply(token: String, threadId: String, message: String) {
        if (token.isBlank()) return
        withContext(Dispatchers.IO) {
            val conn = URL("$BASE_URL:$PORT/api/forum/$threadId?message=$message").openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Content-type", "application/json")
            conn.setRequestProperty("Accept", "appliation/json")

            val inputString = conn.inputStream.bufferedReader().readText()
            Log.e("TAG", "addReply: $inputString", )
        }
    }
    suspend fun deleteReply(token: String, forumId: String, replyId: String) {
        if (token.isBlank()) return
        withContext(Dispatchers.IO) {
            val conn = URL("$BASE_URL:$PORT/api/forum/$forumId/$replyId").openConnection() as HttpURLConnection
            conn.requestMethod = "DELETE"
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Content-type", "application/json")

            Log.e("tag", "deleteReply: ${conn.responseCode} ${conn.url}", )
        }
    }
    suspend fun deletePost(token: String, forumId: String) {
        if (token.isBlank()) return
        withContext(Dispatchers.IO) {
            val conn = URL("$BASE_URL:$PORT/api/forum/$forumId").openConnection() as HttpURLConnection
            conn.requestMethod = "DELETE"
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Content-type", "application/json")

            Log.e("TAG", "deletePost: ${conn.responseCode}", )
        }
    }
}