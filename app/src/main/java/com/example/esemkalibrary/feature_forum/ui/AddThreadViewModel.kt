package com.example.esemkalibrary.feature_forum.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_forum.data.AddThreadUiState
import com.example.esemkalibrary.feature_forum.data.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddThreadViewModel(val context: Context): ViewModel() {
    private val _uiState = MutableStateFlow(AddThreadUiState())
    val uiState: StateFlow<AddThreadUiState> = _uiState.asStateFlow()

    val token = LocalStorage(context).token

    fun addThread (token: String, subject: String, body: String) {
        viewModelScope.launch {
            ApiService().addThread(token,subject,body)
        }
    }

    fun updateSubject(subject: String) {
        _uiState.update {
            it.copy(subject = subject)
        }
    }

    fun updateBody(body: String) {
        _uiState.update {
            it.copy(body = body)
        }
    }
}