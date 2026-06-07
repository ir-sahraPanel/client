package ir.sahrapanel.app.core.data.local.db.entity

// data/local/db/entity/SalonEntity.kt
import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import ir.sahrapanel.app.core.domain.UserRole
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(
    tableName = "salons",
    foreignKeys = [
        ForeignKey(
            entity = ProvinceEntity::class,
            parentColumns = ["id"],
            childColumns = ["province_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["id"],
            childColumns = ["city_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("province_id"), Index("city_id")]
)
@OptIn(ExperimentalUuidApi::class)
data class SalonEntity(
    @PrimaryKey val id: Uuid,
    val name: String,
    val logo: String?,
    val active: Boolean = true,
    @ColumnInfo(name = "province_id") val provinceId: Long?,
    @ColumnInfo(name = "city_id") val cityId: Long?,
    @ColumnInfo(name = "created_at") val createdAt: Instant?,
    @ColumnInfo(name = "updated_at") val updatedAt: Instant?,
    @ColumnInfo(name = "user_role") val userRole: UserRole
)