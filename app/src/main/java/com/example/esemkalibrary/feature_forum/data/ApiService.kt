package com.example.esemkalibrary.feature_forum.data

import android.util.Log
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.feature_myprofile.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONArray
import org.json.JSONObject
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

    fun getThreadDetail(token: String, threadId: String): Flow<ForumDetailUiState> = flow {
        if (token.isBlank()) {
            emit(ForumDetailUiState())
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
        emit(ForumDetailUiState(
            MainPost(
                subject = jsonObject.getString("subject"),
                body = jsonObject.getString("body"),
                createdAt = LocalDate.parse(jsonObject.getString("createdAt").substring(0,10)),
                createdBy = User(
                    name = jsonObject.getJSONObject("createdBy").getString("name"),
                    email = jsonObject.getJSONObject("createdBy").getString("email"),
                ),
                replies = replies,
            )
        ))
    }.flowOn(Dispatchers.IO)
}