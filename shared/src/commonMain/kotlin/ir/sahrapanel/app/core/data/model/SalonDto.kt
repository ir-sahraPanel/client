package ir.sahrapanel.app.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
@Serializable
data class SalonDto(
    @SerialName("id")
    val id: Uuid,

    @SerialName("name")
    val name: String,

    @SerialName("logo")
    val logo: String?,

    @SerialName("city_id")
    val cityId: Long,

    @SerialName("province_id")
    val provinceId: Long,

    @SerialName("is_active")
    val isActive: Boolean,

    @SerialName("is_selected")
    val isSelected: Boolean = false
)