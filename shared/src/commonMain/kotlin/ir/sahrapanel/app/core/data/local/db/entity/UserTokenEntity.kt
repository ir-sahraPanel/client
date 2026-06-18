package ir.sahrapanel.app.core.data.local.db.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import ir.sahrapanel.app.core.domain.UserRole
import ir.sahrapanel.app.features.salon.data.SalonMembershipDto
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@ExperimentalUuidApi
@Entity(tableName = "user_tokens")
data class UserTokenEntity(
    @PrimaryKey
    val id: Uuid, // Acts as the primary key for the whole row
    val phoneNumber: String,
    val firstName: String = "",
    val lastName: String = "",
    val isActive: Boolean = true,
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpire: Instant,
    val refreshTokenExpire: Instant
)

