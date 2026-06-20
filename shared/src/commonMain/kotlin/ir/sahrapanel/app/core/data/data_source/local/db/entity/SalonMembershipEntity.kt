@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.data_source.local.db.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import ir.sahrapanel.app.core.domain.UserRole
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(
    tableName = "salon_memberships",
    primaryKeys = ["salonId", "role"]
)
data class SalonMembershipEntity(
    val salonId: Uuid,
    val role: UserRole,

    @ColumnInfo(defaultValue = "0")
    val isDefault: Boolean = false
)