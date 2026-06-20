package ir.sahrapanel.app.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CityDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("slug") val slug: String,
    @SerialName("province_id") val provinceId: Long
)