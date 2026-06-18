@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.local.db.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import ir.sahrapanel.app.core.domain.UserRole
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(tableName = "salon_memberships")
data class SalonMembershipEntity(
    @PrimaryKey val salonId: Uuid,
    val roles: List<UserRole>
)