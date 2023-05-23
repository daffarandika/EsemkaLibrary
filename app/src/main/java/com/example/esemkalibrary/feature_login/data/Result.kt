package com.example.esemkalibrary.feature_login.data

sealed class Result<out R> private constructor(){
    object Loading: Result<Nothing>()
    data class Error(val message: String): Result<Nothing>()
    data class Success<out T>(val data: T): Result<T>()
}
