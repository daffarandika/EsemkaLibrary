package com.example.esemkalibrary.feature_login.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_login.data.ApiService
import com.example.esemkalibrary.feature_login.data.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(context: Context): ViewModel() {

    init {
        viewModelScope.launch{ LocalStorage(context).clearToken() }
    }

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

    fun updateLoginErrorMessage(errorMessage: String?) {
        _uiState.update{
            it.copy(loginErrorMessage = errorMessage)
        }
    }
    fun isReadyToLogin(): Boolean = (uiState.value.email.isNotBlank() and uiState.value.password.isNotBlank())


    fun getAndSaveToken() {
        val apiService = ApiService()
        viewModelScope.launch {
            apiService.getToken(uiState.value.email, uiState.value.password).catch { err ->
                _uiState.update {uiState ->
                    uiState.copy(loginErrorMessage = err.message)
                }
            }.collect { token ->
                Log.e("TAG", "getAndSaveToken: $token", )
                if(token == "error") {
                    _uiState.update { state ->
                        state.copy(loginErrorMessage = "Invalid username or password")
                    }
                }
                dataStore.setToken(token)
            }
            _uiState.update {
                it.copy(loginErrorMessage = null)
            }
        }
    }


    val token = dataStore.token
}
