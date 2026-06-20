package ir.sahrapanel.app.core.data.dataSource.remote.network

import io.ktor.client.statement.HttpResponse
import ir.sahrapanel.app.core.ui.components.TranslatableText
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.error_no_internet
import ir.sahrapanel.app.shared.error_timeout
import ir.sahrapanel.app.shared.error_unknown

sealed class AppNetworkException() : Exception() {
    // For local network issues (Timeout, No Internet, etc.)
    data class LocalNetworkException(val errorType: NetworkErrorType) : AppNetworkException()

    // For backend-returned errors (e.g., 400, 500, or isSuccess = false)
    data class ServerApiException(
        val status: Int,
        override val message: String,
        val response: HttpResponse? = null
    ) : AppNetworkException()
}

enum class NetworkErrorType {
    TIMEOUT,
    NO_INTERNET,
    UNKNOWN
}
fun Throwable.toTranslatableText(): TranslatableText {
    return when (this) {
        is AppNetworkException.LocalNetworkException -> {
            when (this.errorType) {
                NetworkErrorType.TIMEOUT -> TranslatableText.Resource(Res.string.error_timeout)
                NetworkErrorType.NO_INTERNET -> TranslatableText.Resource(Res.string.error_no_internet)
                NetworkErrorType.UNKNOWN -> TranslatableText.Resource(Res.string.error_unknown)
            }
        }
        is AppNetworkException.ServerApiException -> {
            if (this.message.isNotBlank()) {
                TranslatableText.DynamicString(this.message)
            } else {
                TranslatableText.Resource(Res.string.error_unknown)
            }
        }
        else -> TranslatableText.Resource(Res.string.error_unknown)
    }
}