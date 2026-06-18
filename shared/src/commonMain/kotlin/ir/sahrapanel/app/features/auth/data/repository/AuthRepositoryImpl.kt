@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.features.auth.data.repository

import androidx.room3.useWriterConnection
import ir.sahrapanel.app.core.data.local.db.AppDatabase
import ir.sahrapanel.app.core.data.local.db.dao.SalonDao
import ir.sahrapanel.app.core.data.local.db.dao.SalonMembershipDao
import ir.sahrapanel.app.core.data.local.db.dao.UserTokenDao
import ir.sahrapanel.app.features.auth.data.AuthRemoteDataSource
import ir.sahrapanel.app.features.auth.data.dto.ConfirmOtpResponse
import ir.sahrapanel.app.features.auth.data.dto.UserTokenDto
import ir.sahrapanel.app.features.auth.data.dto.toDomain
import ir.sahrapanel.app.features.auth.data.dto.toEntity
import ir.sahrapanel.app.features.auth.domain.model.AuthSession
import ir.sahrapanel.app.features.auth.domain.model.UserToken
import ir.sahrapanel.app.features.auth.domain.model.toEntity
import ir.sahrapanel.app.features.auth.domain.repository.AuthRepository
import ir.sahrapanel.app.features.salon.data.toEntity
import kotlin.uuid.ExperimentalUuidApi

class AuthRepositoryImpl (    private val remote: AuthRemoteDataSource,
                              private val userTokenDao: UserTokenDao,
                              private val salonDao: SalonDao,
                              private val salonMembershipDao: SalonMembershipDao,
                              private val appDatabase: AppDatabase
) : AuthRepository {
    override suspend fun generateOtp(phoneNumber: String): Result<Unit> =
        remote.generateOtp(phoneNumber)

    override suspend fun confirmOtp(phoneNumber: String, code: String): Result<AuthSession> =
        remote.confirmOtp(phoneNumber, code)
            .onSuccess { otpConfirmResponse ->
                appDatabase.useWriterConnection {

                    userTokenDao.insertOrUpdateToken(otpConfirmResponse.userToken.toEntity())
                    salonMembershipDao.insertAllMemberships(otpConfirmResponse.memberships.map { it.toEntity() })
                    salonDao.insertSalons(otpConfirmResponse.salons.map { it.toEntity() })
                }
            }
            .map(ConfirmOtpResponse::toDomain)

    override suspend fun refreshToken(): Result<UserToken> {
        val storedRefresh = userTokenDao.getCurrentUserToken()?.refreshToken
            ?: return Result.failure(IllegalStateException("no refresh token stored"))
        return remote.refreshToken(storedRefresh)
            .map(UserTokenDto::toDomain)
            .onSuccess { userTokenDao.insertOrUpdateToken(it.toEntity()) }
            .onFailure { userTokenDao.clearSession() }
    }

    override suspend fun isLoggedIn(): Boolean = userTokenDao.getCurrentUserToken() != null

    override suspend fun logout() = userTokenDao.clearSession()
}