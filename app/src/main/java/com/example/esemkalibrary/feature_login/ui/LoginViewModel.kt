package com.example.esemkalibrary.feature_login.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.core.model.Output
import com.example.esemkalibrary.feature_login.data.ApiService
import com.example.esemkalibrary.feature_login.data.LoginUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UiState(val isLoading: Boolean, val data: String? = null, val error: String? = null)

class LoginViewModel(context: Context): ViewModel() {

    private val dataStore = LocalStorage(context)

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _loginState = MutableStateFlow<Output<String>>(Output.Loading)
    val loginState: StateFlow<Output<String>> = _loginState.asStateFlow()

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


    fun getAndSaveToken() {
        val apiService = ApiService()
        viewModelScope.launch {

                apiService.getToken(
                    uiState.value.email,
                    uiState.value.password
                ).onStart {
                    _loginState.value = Output.Loading
                }.catch { e ->
                    _loginState.value = Output.Error(exception = e)
                }.collect { response ->
                    dataStore.setToken(response)
                    _loginState.value = Output.Success(response)
                }
            }
        Log.e("TAG", "LoginViewModel: ${_uiState.value}")
    }


    val token = dataStore.token
}