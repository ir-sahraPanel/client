package ir.sahrapanel.app.core.data.dataSource.local.db.converters


import androidx.room3.ColumnTypeConverter
import ir.sahrapanel.app.core.domain.Gender
import ir.sahrapanel.app.core.domain.UserRole
import kotlin.time.Instant
import org.koin.core.component.inject


import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


    object RoomConverters: KoinComponent {
        private val json: Json by inject<Json>()
    @ColumnTypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(it) }
    }

    @ColumnTypeConverter
    fun instantToTimestamp(instant: Instant?): Long? {
        return instant?.toEpochMilliseconds()
    }

    // --- For LocalDateTime (If you want to save it as an ISO-8601 String) ---
    @ColumnTypeConverter
    fun fromIsoString(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @ColumnTypeConverter
    fun localDateTimeToString(dateTime: LocalDateTime?): String? {
        return dateTime?.toString() // Saves as YYYY-MM-DDTHH:MM:SS
    }


    @OptIn(ExperimentalUuidApi::class)
    @ColumnTypeConverter
    fun fromKotlinUuid(uuid: Uuid?): String? {
        return uuid?.toString()
    }

    @OptIn(ExperimentalUuidApi::class)
    @ColumnTypeConverter
    fun toKotlinUuid(value: String?): Uuid? {
        return value?.let { Uuid.parse(it) }
    }

    @ColumnTypeConverter
    fun fromRoleList(roles: List<UserRole>): String {
        return roles.joinToString(",") { it.name }
    }

    @ColumnTypeConverter
    fun toRoleList(data: String): List<UserRole> {
        if (data.isEmpty()) return emptyList()
        return data.split(",").map { UserRole.valueOf(it) }
    }


    @ColumnTypeConverter
    fun fromGender(gender: Gender): String = gender.name

    @ColumnTypeConverter
    fun toGender(value: String): Gender = try {
        Gender.valueOf(value.uppercase())
    } catch (e: Exception) {
        Gender.UNSPECIFIED
    }
}