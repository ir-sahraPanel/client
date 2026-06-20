package ir.sahrapanel.app.core.data.dataSource.local.db.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(
    tableName = "employee_services",
    foreignKeys = [
        ForeignKey(entity = _root_ide_package_.ir.sahrapanel.app.core.data.dataSource.local.db.entity.EmployeeEntity::class, parentColumns = ["id"], childColumns = ["employee_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = _root_ide_package_.ir.sahrapanel.app.core.data.dataSource.local.db.entity.SalonEntity::class, parentColumns = ["id"], childColumns = ["salon_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("employee_id"), Index("salon_id")]
)
data class EmployeeServiceEntity(
    @PrimaryKey val id: Uuid,
    @ColumnInfo(name = "employee_id") val employeeId: Uuid,
    @ColumnInfo(name = "salon_id") val salonId: Uuid,
    val title: String,
    val description: String?,
    val price: Long,
    @ColumnInfo(name = "salon_commission_rate") val salonCommissionRate: Long, // طبق V0025 اجباری شد و طبق V0029 تغییر نام یافت
    @ColumnInfo(name = "is_active") val isActive: Boolean = true, // تغییر نام طبق V0016
    @ColumnInfo(name = "duration_minutes") val durationMinutes: Int
)