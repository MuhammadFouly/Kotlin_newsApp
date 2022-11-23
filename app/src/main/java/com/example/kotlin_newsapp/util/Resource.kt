package com.example.kotlin_newsapp.util

sealed class Resource<T>(
    val Data: T? = null,
    val massage: String? = null,
) {
    class Success<T>(Data: T?) : Resource<T>(Data)
    class Error<T>(massage: String?, Data: T? = null) : Resource<T>(Data, massage)
    class Loading<T> : Resource<T>()
}