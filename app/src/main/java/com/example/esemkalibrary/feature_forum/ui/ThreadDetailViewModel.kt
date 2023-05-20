package com.example.esemkalibrary.feature_forum.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_forum.data.ApiService
import com.example.esemkalibrary.feature_forum.data.ForumDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ThreadDetailViewModel(val context: Context): ViewModel() {
    val token = LocalStorage(context).token
    fun getThreadDetail(token: String, threadId: String): Flow<ForumDetailUiState>  {
        return ApiService().getThreadDetail(token, threadId).flowOn(Dispatchers.Main)
    }
}