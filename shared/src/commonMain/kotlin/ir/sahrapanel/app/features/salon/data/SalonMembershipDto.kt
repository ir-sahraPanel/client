@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.features.salon.data

import ir.sahrapanel.app.core.data.local.db.entity.SalonMembershipEntity
import ir.sahrapanel.app.core.domain.UserRole
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class SalonMembershipDto(
    @SerialName("salon_id") val salonId: Uuid,
    @SerialName("roles") val roles: List<UserRole>
)

fun SalonMembershipDto.toEntity() : SalonMembershipEntity{
    return SalonMembershipEntity(
        salonId = salonId,
        roles = roles
    )
}