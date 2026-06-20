package ir.sahrapanel.app.core.data.data_source.remote.network

import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.io.IOException

suspend inline fun <reified T> HttpResponse.toResult(): Result<T> {
    return try {
        val apiResponse = this.body<ApiResponse<T>>()
        if (this.status.isSuccess() && apiResponse.isSuccess) {
            // Safe handling for Unit/Empty responses
            Result.success(apiResponse.data ?: (Unit as T))
        } else {
            // Server returned a failing status or flag, use the backend message
            Result.failure(AppNetworkException.ServerApiException(apiResponse.status, apiResponse.message, this))
        }
    } catch (e: Exception) {
        // Serialization or unexpected format error
        Result.failure(e)
    }
}




suspend inline fun <reified T> safeApiCall(
    crossinline request: suspend () -> HttpResponse
): Result<T> = try {
    request().toResult<T>()
} catch (e: HttpRequestTimeoutException) {
    Result.failure(
        AppNetworkException.LocalNetworkException(
            NetworkErrorType.TIMEOUT))
} catch (e: IOException) {
    Result.failure(
        AppNetworkException.LocalNetworkException(
            NetworkErrorType.NO_INTERNET))
} catch (e: ResponseException) {
    // 4xx or 5xx error caught here. We parse the error body manually.
    try {
        val apiResponse = e.response.body<ApiResponse<T>>()
        Result.failure(AppNetworkException.ServerApiException(apiResponse.status, apiResponse.message, e.response))
    } catch (parsingException: Exception) {
        // Fallback if the server error body doesn't match ApiResponse structure
        Result.failure(
            AppNetworkException.LocalNetworkException(
                NetworkErrorType.UNKNOWN))
    }
} catch (e: Exception) {
    Result.failure(
        AppNetworkException.LocalNetworkException(
            NetworkErrorType.UNKNOWN))
}