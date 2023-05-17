package com.example.esemkalibrary.feature_login.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_login.data.ApiService
import com.example.esemkalibrary.feature_login.data.LoginUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(context: Context): ViewModel() {

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
    fun isReadyToLogin(): Boolean = (uiState.value.email.isNotBlank() and uiState.value.password.isNotBlank())

    private val apiService = ApiService()
    fun getAndSaveToken() {
        viewModelScope.launch {
            apiService.getToken(uiState.value.email, uiState.value.password).collect {
                dataStore.setToken(it)
            }
        }
    }

    val token = dataStore.token
}
