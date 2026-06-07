package ir.sahrapanel.app.core.data.local.db.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(
    tableName = "client_invoice_service_items",
    foreignKeys = [
        ForeignKey(entity = ClientInvoiceEntity::class, parentColumns = ["id"], childColumns = ["client_invoice_id"], onDelete = ForeignKey.CASCADE), // طبق V23 به CASCADE تغییر یافت
        ForeignKey(entity = EmployeeServiceEntity::class, parentColumns = ["id"], childColumns = ["employee_services_id"], onDelete = ForeignKey.RESTRICT)
    ],
    indices = [Index("client_invoice_id"), Index("employee_services_id")]
)
data class ClientInvoiceServiceItemEntity(
    @PrimaryKey val id: Uuid,
    @ColumnInfo(name = "client_invoice_id") val clientInvoiceId: Uuid, // طبق V23 مقدار NOT NULL گرفت
    @ColumnInfo(name = "employee_services_id") val employeeServicesId: Uuid, // طبق V23 مقدار NOT NULL گرفت
    val discount: Long = 0,
    val tax: Long = 0
    // فیلدهای employee_share و salon_share حذف شدند (V28)
    // فیلد total حذف شد (V30)
)