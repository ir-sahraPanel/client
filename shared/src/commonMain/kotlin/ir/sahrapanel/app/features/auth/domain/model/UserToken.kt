@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.features.auth.domain.model

import ir.sahrapanel.app.core.data.local.db.entity.UserTokenEntity
import ir.sahrapanel.app.core.util.toUtcInstant
import ir.sahrapanel.app.features.salon.data.SalonMembershipDto
import ir.sahrapanel.app.features.salon.domain.SalonMembership
import kotlin.uuid.ExperimentalUuidApi


data class UserToken(
    val user: User,
    val token: AuthToken,
)

data class UserTokenWithSalonMemberShips(
    val userToken: UserToken,
    val memberships: List<SalonMembership>
)

fun UserToken.toEntity(): UserTokenEntity {
    @OptIn(ExperimentalUuidApi::class)
    return UserTokenEntity(
        id = this.user.id,
        phoneNumber = this.user.phoneNumber,
        firstName = this.user.firstName,
        lastName = this.user.lastName,
        isActive = this.user.isActive,
        accessToken = this.token.accessToken,
        refreshToken = this.token.refreshToken,
        accessTokenExpire = this.token.accessTokenExpire.toUtcInstant(),
        refreshTokenExpire = this.token.refreshTokenExpire.toUtcInstant(),
    )
}
