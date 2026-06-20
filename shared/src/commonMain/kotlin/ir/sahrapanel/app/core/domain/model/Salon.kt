@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.domain.model // Or your core domain package

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Salon(
    val id: Uuid,
    val name: String,
    val logo: String?,
    val cityId: Long,
    val provinceId: Long,
    val isActive: Boolean
)