package ir.sahrapanel.app.features.auth.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ir.sahrapanel.app.core.data.remote.safeApiCall
import ir.sahrapanel.app.features.auth.data.dto.AuthGenerateOtpRequest
import ir.sahrapanel.app.features.auth.data.dto.AuthOtpConfirmRequest
import ir.sahrapanel.app.features.auth.data.dto.ConfirmOtpResponse
import ir.sahrapanel.app.features.auth.data.dto.RefreshTokenRequest
import ir.sahrapanel.app.features.auth.data.dto.UserTokenDto

class AuthRemoteDataSource(private val client: HttpClient) {
    suspend fun generateOtp(phoneNumber: String): Result<Unit> = safeApiCall {
        client.post("auth/otp/generate") {
            contentType(ContentType.Application.Json)
            setBody(AuthGenerateOtpRequest(phoneNumber))
        }.body()
    }

    suspend fun confirmOtp(phoneNumber: String, code: String): Result<ConfirmOtpResponse> = safeApiCall {
        client.post("auth/otp/confirm") {
            contentType(ContentType.Application.Json)
            setBody(AuthOtpConfirmRequest(phoneNumber, code))
        }.body()
    }

    suspend fun refreshToken(refreshToken: String): Result<UserTokenDto> = safeApiCall {
        client.post("auth/token/refresh") {
            contentType(ContentType.Application.Json)
            setBody(RefreshTokenRequest(refreshToken))
        }.body()
    }
}