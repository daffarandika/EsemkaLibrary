package com.example.esemkalibrary.feature_myprofile.ui

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.esemkalibrary.core.data.LocalStorage
import com.example.esemkalibrary.feature_myprofile.data.ApiService
import com.example.esemkalibrary.feature_myprofile.data.CartItem
import com.example.esemkalibrary.feature_myprofile.data.ProfileUiState
import com.example.esemkalibrary.feature_myprofile.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File

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
    fun updateProfilePhoto(profilePhoto: ImageBitmap) {
//        viewModelScope.launch {
//            val file: File = profilePhoto.
//        }
    }

}