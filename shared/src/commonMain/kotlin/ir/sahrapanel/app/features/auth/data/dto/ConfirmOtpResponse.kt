package ir.sahrapanel.app.features.auth.data.dto

import ir.sahrapanel.app.features.auth.domain.model.AuthSession
import ir.sahrapanel.app.core.data.model.SalonDto
import ir.sahrapanel.app.core.data.model.SalonMembershipDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class ConfirmOtpResponse(
    @SerialName("user_token") val userToken: UserTokenDto,
    @SerialName("salons") val salons: List<SalonDto> = emptyList(),
    @SerialName("memberships") val memberships: List<SalonMembershipDto> = emptyList()
)
fun ConfirmOtpResponse.toDomain(): AuthSession {
    return AuthSession(
        userToken = this.userToken.toDomain(),
        hasSalon = this.salons.isNotEmpty()
    )
}