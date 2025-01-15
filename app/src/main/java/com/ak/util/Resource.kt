package com.ak.util

open class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val responseCode: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, responseCode: Int) : Resource<T>(data, message,responseCode)
    class Loading<T> : Resource<T>()
}