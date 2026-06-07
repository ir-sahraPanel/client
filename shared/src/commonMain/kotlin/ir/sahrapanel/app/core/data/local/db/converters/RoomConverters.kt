package ir.sahrapanel.app.core.data.local.db.converters


import androidx.room3.TypeConverter
import ir.sahrapanel.app.core.domain.Gender
import ir.sahrapanel.app.core.domain.UserRole
import kotlin.time.Instant


import kotlinx.datetime.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class RoomConverters {

    // --- For Instant (Recommended for precise timestamps) ---
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(it) }
    }

    @TypeConverter
    fun instantToTimestamp(instant: Instant?): Long? {
        return instant?.toEpochMilliseconds()
    }

    // --- For LocalDateTime (If you want to save it as an ISO-8601 String) ---
    @TypeConverter
    fun fromIsoString(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun localDateTimeToString(dateTime: LocalDateTime?): String? {
        return dateTime?.toString() // Saves as YYYY-MM-DDTHH:MM:SS
    }


    @TypeConverter
    fun fromKotlinUuid(uuid: Uuid?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toKotlinUuid(value: String?): Uuid? {
        return value?.let { Uuid.parse(it) }
    }

    @TypeConverter
    fun fromUserRole(role: UserRole): String = role.name

    @TypeConverter
    fun toUserRole(value: String): UserRole = try {
        UserRole.valueOf(value)
    } catch (e: Exception) {
        UserRole.UNKNOWN
    }

    @TypeConverter
    fun fromGender(gender: Gender): String = gender.name

    @TypeConverter
    fun toGender(value: String): Gender = try {
        Gender.valueOf(value.uppercase())
    } catch (e: Exception) {
        Gender.UNSPECIFIED
    }
}