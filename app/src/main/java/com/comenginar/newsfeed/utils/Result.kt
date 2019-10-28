package com.comenginar.newsfeed.utils

sealed class Result<out T> {
    class Success<out T : Any>(val data: T) : Result<T>()
    class Error(val exception: Exception) : Result<Nothing>()
    class Loading<out T : Any> : Result<T>()
}