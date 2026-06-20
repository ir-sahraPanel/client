package ir.sahrapanel.app.core.data.dataSource.local.db.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey
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

