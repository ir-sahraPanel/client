package ir.sahrapanel.app.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserTokenDto(
    val user: UserDto,
    val token: TokenDto,
)