package ir.sahrapanel.app.features.auth.data.repository

import ir.sahrapanel.app.core.data.local.secure_storage.UserStorage
import ir.sahrapanel.app.features.auth.data.AuthRemoteDataSource
import ir.sahrapanel.app.features.auth.data.dto.UserTokenDto
import ir.sahrapanel.app.features.auth.data.dto.toDomain
import ir.sahrapanel.app.features.auth.domain.model.UserToken
import ir.sahrapanel.app.features.auth.domain.repository.AuthRepository

class AuthRepositoryImpl (    private val remote: AuthRemoteDataSource,
                              private val storage: UserStorage): AuthRepository {
    override suspend fun generateOtp(phoneNumber: String): Result<Unit> =
        remote.generateOtp(phoneNumber)

    override suspend fun confirmOtp(phoneNumber: String, code: String): Result<UserToken> =
        remote.confirmOtp(phoneNumber, code)
            .map(UserTokenDto::toDomain)
            .onSuccess { storage.saveUserToken(it) }

    override suspend fun refreshToken(): Result<UserToken> {
        val storedRefresh = storage.getRefreshToken()
            ?: return Result.failure(IllegalStateException("no refresh token stored"))
        return remote.refreshToken(storedRefresh)
            .map(UserTokenDto::toDomain)
            .onSuccess { storage.saveUserToken(it) }
            .onFailure { storage.clear() }      // refresh failed → force logout
    }

    override suspend fun isLoggedIn(): Boolean =  storage.getAccessToken() != null

    override suspend fun logout() = storage.clear()
}