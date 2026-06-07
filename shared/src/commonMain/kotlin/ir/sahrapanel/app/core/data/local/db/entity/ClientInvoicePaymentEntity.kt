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
    tableName = "client_invoice_payments",
    foreignKeys = [
        ForeignKey(entity = ClientInvoiceEntity::class, parentColumns = ["id"], childColumns = ["client_invoice_id"], onDelete = ForeignKey.RESTRICT)
    ],
    indices = [Index("client_invoice_id")]
)
data class ClientInvoicePaymentEntity(
    @PrimaryKey val id: Uuid,
    @ColumnInfo(name = "client_invoice_id") val clientInvoiceId: Uuid,
    @ColumnInfo(name = "payment_date") val paymentDate: Instant, // طبق V26 مقدار NOT NULL گرفت
    val amount: Long,
    @ColumnInfo(name = "payment_method") val paymentMethod: String = "cash", // cash, card, online, wallet, other
    @ColumnInfo(name = "payment_status") val paymentStatus: String = "pending", // pending, paid, late, refunded
    val recipient: String = "salon", // salon, employee, BOTH (طبق V24 مقدار BOTH اضافه شد)
    val notes: String?
)