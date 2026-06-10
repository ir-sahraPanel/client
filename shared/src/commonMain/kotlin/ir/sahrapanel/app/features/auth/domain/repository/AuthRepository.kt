package ir.sahrapanel.app.features.auth.domain.repository

import ir.sahrapanel.app.features.auth.data.dto.UserTokenDto
import ir.sahrapanel.app.features.auth.domain.model.UserToken

interface AuthRepository {
    suspend fun generateOtp(phoneNumber: String): Result<Unit>
    suspend fun confirmOtp(phoneNumber: String, code: String): Result<UserToken>
    suspend fun refreshToken(): Result<UserToken>          // reads stored token internally
  suspend  fun isLoggedIn(): Boolean
    suspend fun logout()
}