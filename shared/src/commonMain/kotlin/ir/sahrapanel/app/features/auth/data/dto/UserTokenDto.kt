package ir.sahrapanel.app.features.auth.data.dto

import ir.sahrapanel.app.core.util.toUtcInstant
import ir.sahrapanel.app.features.auth.domain.model.AuthToken
import ir.sahrapanel.app.features.auth.domain.model.User
import ir.sahrapanel.app.features.auth.domain.model.UserToken
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class UserTokenDto(
    val user: UserDto,
    val token: AuthTokenDto,
)


@OptIn(ExperimentalUuidApi::class)
fun UserTokenDto.toDomain() = UserToken(
    user = User(
        id = Uuid.parse(user.id),
        phoneNumber = user.phoneNumber,
        firstName = user.firstName,
        lastName = user.lastName,
        isActive = user.isActive,
    ),
    token = AuthToken(
        accessToken = token.accessToken,
        refreshToken = token.refreshToken,
        accessTokenExpire = token.accessTokenExpire,
        refreshTokenExpire = token.refreshTokenExpire,
    ),
)
@OptIn(ExperimentalUuidApi::class)
fun UserTokenDto.toEntity() =
    _root_ide_package_.ir.sahrapanel.app.core.data.data_source.local.db.entity.UserTokenEntity(
        id = Uuid.parse(user.id),
        phoneNumber = user.phoneNumber,
        firstName = user.firstName,
        lastName = user.lastName,
        isActive = user.isActive,
        accessToken = token.accessToken,
        refreshToken = token.refreshToken,
        accessTokenExpire = token.accessTokenExpire.toUtcInstant(),
        refreshTokenExpire = token.refreshTokenExpire.toUtcInstant()

    )
