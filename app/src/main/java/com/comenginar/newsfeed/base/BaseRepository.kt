package com.comenginar.newsfeed.base

import android.util.Log
import com.comenginar.newsfeed.utils.Result
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {

        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - ${response.message()} - ${response.code()}"))
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {

            is Result.Success -> data = result.data
            is Result.Error -> {
                Log.d("Data Repository", "$errorMessage & Exception - ${result.exception}")
            }

        }
        return data
    }

}