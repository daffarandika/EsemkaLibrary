package com.example.esemkalibrary.feature_myprofile.ui

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.ApiConfig.BASE_URL
import com.example.esemkalibrary.core.data.ApiConfig.PORT
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_myprofile.data.ApiService
import com.example.esemkalibrary.feature_myprofile.data.CartItem
import com.example.esemkalibrary.feature_myprofile.data.ProfileUiState
import com.example.esemkalibrary.feature_myprofile.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class MyProfileViewModel(context: Context): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    val token = LocalStorage(context).token

    fun getUserDetail(token: String): Flow<User> {
        return ApiService().getUserDetail(token).flowOn(Dispatchers.Main)
    }
    fun getCartHistory(token: String): Flow<List<CartItem>> {
        return ApiService().getCartHistory(token).flowOn(Dispatchers.Main)
    }

    fun updateName(name: String) {
       _uiState.update {
           it.copy(name = name)
       }
    }
    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }
    fun updateCartHistory(cartItems: List<CartItem>) {
        _uiState.update {
            it.copy(borrowingHistory = cartItems)
        }
    }
    fun updateProfilePhoto(profilePhoto: File, token :String) {
        viewModelScope.launch(Dispatchers.IO) {
            val conn = URL("$BASE_URL:$PORT/Api/User/Me/Photo").openConnection() as HttpURLConnection

            val boundary = "*****"
            val lineEnd = "\r\n"

            conn.doOutput = true
            conn.doInput = true
            conn.useCaches = false
            conn.requestMethod = "POST"

            conn.setRequestProperty("Connection", "Keep-Alive")
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")

            val outputStream = DataOutputStream(conn.outputStream)
            val writer = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))

            writer.write("--$boundary$lineEnd")
            writer.write("Content-Disposition: form-data; name=\"file\"; filename=\"${profilePhoto.name}\"$lineEnd")
            writer.write("Content-Type: image/jpeg$lineEnd")
            writer.write(lineEnd)
            writer.flush()

            val fileInputStream = FileInputStream(profilePhoto)
            val bufferSize = 1024
            val buffer = ByteArray(bufferSize)
            var bytesRead: Int

            while (fileInputStream.read(buffer, 0, bufferSize).also { bytesRead = it } >= 0) {
                outputStream.write(buffer, 0, bytesRead)
            }

            outputStream.writeBytes(lineEnd)
            outputStream.writeBytes("--$boundary--$lineEnd")

            writer.close()
            outputStream.flush()
            outputStream.close()
            fileInputStream.close()

            val responseCode = conn.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val response = StringBuilder()
                var responseLine: String?

                while (reader.readLine().also { responseLine = it } != null) {
                    response.append(responseLine)
                }

                reader.close()
                println("Upload successful. Response: $response")
            } else {
                println("Upload failed. Response code: $responseCode")
            }

            conn.disconnect()

        }
    }

}