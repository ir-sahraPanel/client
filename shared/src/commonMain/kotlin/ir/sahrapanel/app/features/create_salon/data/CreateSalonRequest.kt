package ir.sahrapanel.app.features.create_salon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateSalonRequest(
    @SerialName("name") val name: String,
    @SerialName("province_id") val provinceId: Long,
    @SerialName("city_id") val cityId: Long,
    @SerialName("logo") val logo: String? = null,
    @SerialName("owner") val owner: SalonOwnerRequest
)

@Serializable
data class SalonOwnerRequest(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
)