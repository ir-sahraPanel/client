package ir.sahrapanel.app.core.data.local.db.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(
    tableName = "employee_rents",
    foreignKeys = [
        ForeignKey(entity = EmployeeEntity::class, parentColumns = ["id"], childColumns = ["employee_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = SalonEntity::class, parentColumns = ["id"], childColumns = ["salon_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("employee_id"), Index("salon_id")]
)
data class EmployeeRentEntity(
    @PrimaryKey val id: Uuid,
    @ColumnInfo(name = "employee_id") val employeeId: Uuid,
    @ColumnInfo(name = "salon_id") val salonId: Uuid,
    val title: String,
    val description: String?,
    @ColumnInfo(name = "rent_amount") val rentAmount: Long,
    @ColumnInfo(name = "start_date") val startDate: Instant,
    @ColumnInfo(name = "end_date") val endDate: Instant?
)