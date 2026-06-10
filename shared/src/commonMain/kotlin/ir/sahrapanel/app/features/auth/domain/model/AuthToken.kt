package ir.sahrapanel.app.features.auth.domain.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpire: LocalDateTime,
    val refreshTokenExpire: LocalDateTime,
)
