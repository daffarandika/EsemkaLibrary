package com.example.esemkalibrary.feature_myprofile.ui

import android.content.Context
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
            val twoHyphens = "--"
            val boundary = "*****" + java.lang.Long.toString(System.currentTimeMillis()) + "*****"
            val lineEnd = "\r\n"
            val conn = URL("$BASE_URL:$PORT/Api/User/Me/Photo").openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.setRequestProperty("accept", "*/*")
            conn.setRequestProperty("Authorization", "Bearer $token")
            conn.setRequestProperty("Content-type", "multipart/form-data")

            val fileInputStream = FileInputStream(profilePhoto)

            // Create the data output stream to write the request body
            val outputStream = DataOutputStream(conn.outputStream)

            // Start writing the request body
//            outputStream.writeBytes(twoHyphens + boundary + lineEnd)
            outputStream.writeBytes("Content-Disposition: form-data; name=\"file\";type=image/jpeg;filename=\"${profilePhoto.name}\"$lineEnd")
//            outputStream.writeBytes(lineEnd)

            // Read the image file and write it to the output stream
            val bufferSize = 1024*1024*1
            val buffer = ByteArray(bufferSize)
            var bytesRead: Int
            while (fileInputStream.read(buffer, 0, bufferSize).also { bytesRead = it } >= 0) {
                outputStream.write(buffer, 0, bytesRead)
            }

            outputStream.writeBytes(lineEnd)
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd)

            // Flush and close the streams
            outputStream.flush()
            outputStream.close()
            fileInputStream.close()

            val status: Int = conn.responseCode
           if (status == HttpURLConnection.HTTP_NO_CONTENT) {
                val inS = BufferedReader(InputStreamReader(conn.getInputStream()))
                var inputLine: String?
                val response = StringBuffer()
                while (inS.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                conn.disconnect()
                fileInputStream.close()
                outputStream.flush()
                outputStream.close()
                response.toString()
            } else {
                throw Exception("Non ok response returned")
            }

        }
    }

}