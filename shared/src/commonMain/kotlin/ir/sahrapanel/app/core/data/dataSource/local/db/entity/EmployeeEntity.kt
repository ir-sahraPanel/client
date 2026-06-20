package ir.sahrapanel.app.core.data.dataSource.local.db.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "employees")
data class EmployeeEntity(
    @PrimaryKey val id: Uuid,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "is_active") val isActive: Boolean = true,
    @ColumnInfo(name = "profile_picture") val profilePicture: String?,
    val address: String?,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: Instant?, // در کلاینت از Date استفاده می‌کنیم
    @ColumnInfo(name = "national_id") val nationalId: String?
)