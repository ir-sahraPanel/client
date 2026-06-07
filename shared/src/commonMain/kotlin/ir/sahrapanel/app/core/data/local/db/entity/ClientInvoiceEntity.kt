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
    tableName = "client_invoices",
    foreignKeys = [
        ForeignKey(entity = ClientEntity::class, parentColumns = ["id"], childColumns = ["client_id"], onDelete = ForeignKey.RESTRICT),
        ForeignKey(entity = SalonEntity::class, parentColumns = ["id"], childColumns = ["salon_id"], onDelete = ForeignKey.RESTRICT),
        ForeignKey(entity = ExtraServiceEntity::class, parentColumns = ["id"], childColumns = ["extra_services_id"], onDelete = ForeignKey.RESTRICT)
    ],
    indices = [Index("client_id"), Index("salon_id"), Index("extra_services_id")]
)
data class ClientInvoiceEntity(
    @PrimaryKey val id: Uuid,
    @ColumnInfo(name = "client_id") val clientId: Uuid?,
    @ColumnInfo(name = "salon_id") val salonId: Uuid,
    @ColumnInfo(name = "extra_services_id") val extraServicesId: Uuid?,
    @ColumnInfo(name = "invoice_date") val invoiceDate: Instant?,
    val total: Long = 0,
    @ColumnInfo(name = "is_installment") val isInstallment: Boolean = false,
    val status: String = "pending", // مپ شده از دیتابیس (pending, partial, paid, canceled)
    val notes: String?
    // فیلدهای employee_id حذف شد (V23)
    // فیلدهای employee_share و salon_share حذف شدند (V28)
)