package ir.sahrapanel.app.features.auth.domain.repository

import ir.sahrapanel.app.features.auth.domain.model.AuthSession
import ir.sahrapanel.app.features.auth.domain.model.UserToken

interface AuthRepository {
    suspend fun generateOtp(phoneNumber: String): Result<Unit>
    suspend fun confirmOtp(phoneNumber: String, code: String): Result<AuthSession>
    suspend fun refreshToken(): Result<UserToken>
  suspend  fun isLoggedIn(): Boolean
    suspend fun logout()
}