package ir.sahrapanel.app.features.auth

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpire: LocalDateTime,
    val refreshTokenExpire: LocalDateTime,
)