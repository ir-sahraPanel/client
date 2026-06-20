package ir.sahrapanel.app.core.data.dataSource.local.db.entity

// data/local/db/entity/SalonEntity.kt
import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
@Entity(tableName = "salons")
@OptIn(ExperimentalUuidApi::class)
data class SalonEntity(
    @PrimaryKey val id: Uuid = Uuid.random(),
    val name: String,
    val logo: String?,
    @ColumnInfo(name = "active") val isActive: Boolean = true,
    @ColumnInfo(name = "province_id") val provinceId: Long?,
    @ColumnInfo(name = "city_id") val cityId: Long?,
    @ColumnInfo(name = "created_at") val createdAt: Instant?,
    @ColumnInfo(name = "updated_at") val updatedAt: Instant?
)