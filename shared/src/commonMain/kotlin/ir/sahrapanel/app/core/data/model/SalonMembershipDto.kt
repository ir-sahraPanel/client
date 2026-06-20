@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.model

import ir.sahrapanel.app.core.data.data_source.local.db.entity.SalonMembershipEntity
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

fun SalonMembershipDto.toEntities(): List<SalonMembershipEntity> {
    return roles.map {
        SalonMembershipEntity(
            salonId,
            it
        )
    }
}