package ir.sahrapanel.app.features.auth.data.dto

import ir.sahrapanel.app.features.auth.domain.model.AuthToken
import ir.sahrapanel.app.features.auth.domain.model.User
import ir.sahrapanel.app.features.auth.domain.model.UserToken
import kotlinx.serialization.Serializable

@Serializable
data class UserTokenDto(
    val user: UserDto,
    val token: AuthTokenDto,
)

fun UserTokenDto.toDomain() = UserToken(
    user = User(
        id = user.id,
        phoneNumber = user.phoneNumber,
        name = user.name,
        isActive = user.isActive,
    ),
    token = AuthToken(
        accessToken = token.accessToken,
        refreshToken = token.refreshToken,
        accessTokenExpire = token.accessTokenExpire,
        refreshTokenExpire = token.refreshTokenExpire,
    ),
)