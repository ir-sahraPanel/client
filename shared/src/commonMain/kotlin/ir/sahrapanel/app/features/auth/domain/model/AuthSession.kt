package ir.sahrapanel.app.features.auth.domain.model

import ir.sahrapanel.app.features.auth.data.dto.ConfirmOtpResponse
import ir.sahrapanel.app.features.auth.data.dto.toDomain

data class AuthSession(
    val userToken: UserToken,
    val hasSalon: Boolean
)

