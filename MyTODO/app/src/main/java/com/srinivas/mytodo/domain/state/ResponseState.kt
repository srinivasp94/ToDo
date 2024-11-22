package com.srinivas.mytodo.domain.state

sealed class ResponseState<T> {
    class Loading<T> : ResponseState<T>()
    data class Success<T>(val data : T) : ResponseState<T>()
    data class Empty<T>(val emptyText : String) : ResponseState<T>()
    data class Error<T>(val error : Any) : ResponseState<T>()
}