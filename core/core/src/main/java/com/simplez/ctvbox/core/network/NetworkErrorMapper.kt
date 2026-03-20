package com.simplez.ctvbox.core.network

import com.simplez.ctvbox.core.result.AppError
import io.ktor.client.plugins.ResponseException
import io.ktor.network.sockets.SocketTimeoutException
import java.net.ConnectException
import java.net.UnknownHostException
import kotlinx.serialization.SerializationException

class NetworkErrorMapper {

    fun map(throwable: Throwable): AppError {
        return when (throwable) {
            is SocketTimeoutException -> AppError.Timeout
            is UnknownHostException,
            is ConnectException -> AppError.NetworkUnavailable
            is ResponseException -> AppError.Http(throwable.response.status.value)
            is SerializationException -> AppError.DataParsing
            else -> AppError.Unknown(throwable.message)
        }
    }
}
