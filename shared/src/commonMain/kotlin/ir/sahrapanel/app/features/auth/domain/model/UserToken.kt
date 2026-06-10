package ir.sahrapanel.app.features.auth.domain.model

import ir.sahrapanel.app.features.auth.data.dto.UserTokenDto
import kotlinx.serialization.Serializable

@Serializable
data class UserToken(
    val user: User,
    val token: AuthToken,
)
