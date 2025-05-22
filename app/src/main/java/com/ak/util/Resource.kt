package com.ak.util

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(
        val message: String? = null,
        val throwable: Throwable? = null
    ) : Result<Nothing>()
    data object Idle : Result<Nothing>()
}


suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(apiCall())
    } catch (e: Exception) {
        e.printStackTrace()
        Result.Error(e.message ?: "Unknown error", e)
    }
}