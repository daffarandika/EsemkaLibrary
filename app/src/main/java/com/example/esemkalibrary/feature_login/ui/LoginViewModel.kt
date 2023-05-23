package com.example.esemkalibrary.feature_login.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.ApiConfig
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_login.data.ApiService
import com.example.esemkalibrary.feature_login.data.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

sealed class LoginResult {
    object Loading: LoginResult()
    data class Success(val data: String): LoginResult()
    data class Error(val message: String): LoginResult()
}

class LoginViewModel(context: Context): ViewModel() {

    private val _loginResult:MutableStateFlow<LoginResult> = MutableStateFlow(LoginResult.Loading)
    val loginResult: StateFlow<LoginResult> = _loginResult.asStateFlow()

    private val dataStore = LocalStorage(context)

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateEmailInput(email: String) {
        _uiState.update{
            it.copy(email = email)
        }
    }

    fun updatePasswordInput(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun updatePasswordVisibility(isPasswordVisible: Boolean) {
        _uiState.update {
            it.copy(isPasswordVisible = isPasswordVisible)
        }
    }
    fun updatePasswordError(isPasswordError: Boolean) {
        _uiState.update {
            it.copy(isPasswordError = isPasswordError)
        }
    }
    fun updateEmailError(isEmailError: Boolean) {
        _uiState.update {
            it.copy(isEmailError = isEmailError)
        }
    }
    fun updateLoginAllow(isAllowed: Boolean) {
        _uiState.update {
            it.copy(isAllowedToLogin = isAllowed)
        }
    }

    fun isReadyToLogin(): Boolean = (uiState.value.email.isNotBlank() and uiState.value.password.isNotBlank())

    fun setToken(token: String) {
        _uiState.update {
            it.copy(token = token)
        }
        viewModelScope.launch {
            dataStore.setToken(token)
        }
    }


    fun getToken(): Flow<String> {
        return ApiService().getToken(uiState.value.email, uiState.value.password).flowOn(Dispatchers.Main)
    }

    fun performLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
           val conn: HttpURLConnection = URL("${ApiConfig.BASE_URL}:${ApiConfig.PORT}/Api/Auth").openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.doInput = true
            conn.setRequestProperty("Content-Type", "application/json")
            conn.setRequestProperty("Accept", "application/json")
            try {
                conn.outputStream.use {
                    it.write(JSONObject().apply {
                        put("email", email)
                        put("password", password)
                    }.toString().toByteArray())
                }
                val input = conn.inputStream.bufferedReader().readText()
                if (input.isNotEmpty()) {
                    val jsonObject = JSONObject(input)
                    dataStore.setToken(jsonObject.getString("token"))
                    _loginResult.value = LoginResult.Success(jsonObject.getString("token"))
                }
            } catch (e: java.lang.Exception){
                _loginResult.value =LoginResult.Error("invalid login")
            } finally {
                conn.disconnect()
            }

        }
    }

}

