package com.simplez.ctvbox.core.result

sealed interface AppError {
    data object NetworkUnavailable : AppError
    data object Timeout : AppError
    data class Http(val code: Int) : AppError
    data object DataParsing : AppError
    data class Unknown(val message: String?) : AppError
}

fun AppError.userMessage(): String {
    return when (this) {
        AppError.NetworkUnavailable -> "Network unavailable"
        AppError.Timeout -> "Request timeout"
        is AppError.Http -> "Server error: HTTP $code"
        AppError.DataParsing -> "Response parsing failed"
        is AppError.Unknown -> message ?: "Unknown error"
    }
}
