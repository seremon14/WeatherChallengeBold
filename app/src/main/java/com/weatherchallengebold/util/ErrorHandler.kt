package com.weatherchallengebold.util

import android.util.Log
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

object ErrorHandler {
    private const val TAG = "WeatherApp"

    fun getErrorMessage(exception: Throwable): String {
        return when (exception) {
            is UnknownHostException,
            is ConnectException -> {
                "No hay conexión a internet. Verifica tu conexión e intenta nuevamente."
            }

            is SocketTimeoutException -> {
                "El servicio no está disponible en este momento. Intenta más tarde."
            }

            is SSLException -> {
                "Error de conexión segura. Verifica tu conexión e intenta nuevamente."
            }

            is IOException -> {
                "Error de conexión. Verifica tu conexión a internet e intenta nuevamente."
            }

            else -> {
                "El servicio no está disponible en este momento. Intenta más tarde."
            }
        }
    }

    fun isNetworkError(exception: Throwable): Boolean {
        return exception is IOException ||
                exception is UnknownHostException ||
                exception is ConnectException ||
                exception is SocketTimeoutException ||
                exception is SSLException
    }

    fun logError(tag: String, message: String, exception: Throwable) {
        Log.e(tag, message, exception)
        Log.e(tag, "Error type: ${exception.javaClass.simpleName}")
        Log.e(tag, "Error message: ${exception.message}")
        exception.stackTrace.take(5).forEach { element ->
            Log.e(
                tag,
                "  at ${element.className}.${element.methodName}(${element.fileName}:${element.lineNumber})"
            )
        }
    }
}
