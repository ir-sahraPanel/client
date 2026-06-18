package ir.sahrapanel.app.core.location.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ProvinceDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("slug") val slug: String,
    @SerialName("tel_prefix") val telPrefix: String?
)