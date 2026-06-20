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
    tableName = "extra_services",
    foreignKeys = [
        ForeignKey(entity = _root_ide_package_.ir.sahrapanel.app.core.data.dataSource.local.db.entity.EmployeeEntity::class, parentColumns = ["id"], childColumns = ["employee_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("employee_id")]
)
data class ExtraServiceEntity(
    @PrimaryKey val id: Uuid,
    val title: String,
    val amount: Long,
    val notes: String?,
    @ColumnInfo(name = "salon_commission_rate") val salonCommissionRate: Long?, // طبق V0027 اضافه و طبق V0029 ری‌نیم شد
    @ColumnInfo(name = "employee_id") val employeeId: Uuid? // طبق V0027 اضافه شد
)