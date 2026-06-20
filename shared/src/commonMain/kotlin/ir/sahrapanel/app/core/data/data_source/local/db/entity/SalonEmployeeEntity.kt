package ir.sahrapanel.app.core.data.data_source.local.db.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "salon_employees")
data class SalonEmployeeEntity(
    @PrimaryKey val id: Uuid, // طبق V0015 کلید اصلی اضافه شد
    @ColumnInfo(name = "salon_id") val salonId: Uuid,
    @ColumnInfo(name = "employee_id") val employeeId: Uuid,
    @ColumnInfo(name = "base_salary") val baseSalary: Long? // مپ کردن NUMERIC به Long
)