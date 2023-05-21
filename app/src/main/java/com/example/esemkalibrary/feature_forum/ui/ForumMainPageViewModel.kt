package com.example.esemkalibrary.feature_forum.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_forum.data.ApiService
import com.example.esemkalibrary.feature_forum.data.ForumMainPageUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ForumMainPageViewModel(val context: Context): ViewModel() {

    val token = LocalStorage(context).token

    fun getUiState(token: String): Flow<ForumMainPageUiState> {
        return ApiService().getMainUiState(token).flowOn(Dispatchers.Main)
    }

    fun deletePost(token: String, forumId: String) {
        viewModelScope.launch {
            ApiService().deletePost(token, forumId)
        }
    }

}