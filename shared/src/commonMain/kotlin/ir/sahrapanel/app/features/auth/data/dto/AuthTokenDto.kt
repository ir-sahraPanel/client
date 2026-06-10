package ir.sahrapanel.app.features.auth.data.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class AuthTokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpire: LocalDateTime,
    val refreshTokenExpire: LocalDateTime,
)