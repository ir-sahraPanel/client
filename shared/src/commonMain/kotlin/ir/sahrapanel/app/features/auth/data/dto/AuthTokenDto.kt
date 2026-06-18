package ir.sahrapanel.app.features.auth.data.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AuthTokenDto(
    @SerialName("access_token")
    val accessToken: String,

    @SerialName("refresh_token")
    val refreshToken: String,

    @SerialName("access_token_expire")
    val accessTokenExpire: LocalDateTime,

    @SerialName("refresh_token_expire")
    val refreshTokenExpire: LocalDateTime
)