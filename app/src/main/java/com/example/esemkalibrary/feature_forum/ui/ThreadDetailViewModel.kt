package com.example.esemkalibrary.feature_forum.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_forum.data.ApiService
import com.example.esemkalibrary.feature_forum.data.MainPost
import com.example.esemkalibrary.feature_forum.data.ThreadDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ThreadDetailViewModel(val context: Context): ViewModel() {
    val token = LocalStorage(context).token
    private val _uiState = MutableStateFlow(ThreadDetailUiState())
    val uiState: StateFlow<ThreadDetailUiState> = _uiState.asStateFlow()

    fun getThreadDetail(token: String, threadId: String): Flow<ThreadDetailUiState>  {
        return ApiService().getThreadDetail(token, threadId).flowOn(Dispatchers.Main)
    }

    fun updateReplyText(reply:String) {
        _uiState.update {
            it.copy(replyText = reply)
        }
    }

    fun updateCurrentUsername(username: String) {
        _uiState.update {
            it.copy(currentUsername = username)
        }
        Log.e("TAG", "currentUsername: ${_uiState.value.currentUsername}", )
    }

    fun updateMainPost(mainPost: MainPost) {
        _uiState.update {
            it.copy(mainPost = mainPost)
        }
    }

    fun postReply(token: String, threadId: String, message: String) {
        viewModelScope.launch {
            ApiService().addReply(token, threadId, message)
        }
    }
}