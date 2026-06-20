package ir.sahrapanel.app.core.data.dataSource.local.db.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import ir.sahrapanel.app.core.domain.Gender
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(
    tableName = "clients",
    indices = [Index(value = ["phone"], unique = true)]
)
data class ClientEntity(
    @PrimaryKey val id: Uuid,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val phone: String,
    @ColumnInfo(name = "birth_date") val birthDate: Instant?,
    val gender: Gender,
    val address: String?,
    val occupation: String?,
    val notes: String?
)