@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.features.salon.data

import ir.sahrapanel.app.core.data.local.db.entity.SalonEntity
import ir.sahrapanel.app.features.salon.domain.Salon
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi

// Convert a single DTO to Domain
fun SalonDto.toDomain(): Salon {
    return Salon(
        id = id,
        name = name,
        logo = logo,
        cityId = cityId,
        provinceId = provinceId,
        isActive = isActive
    )
}
fun SalonDto.toEntity(): SalonEntity {
    return SalonEntity(
        id = id,
        name = name,
        logo = logo,
        cityId = cityId,
        provinceId = provinceId,
        isActive = isActive,
        createdAt = Clock.System.now(),//todo change later
        updatedAt = Clock.System.now(),
    )
}

// Convert a Domain model back to DTO (if needed for POST/PUT requests)
fun Salon.toDto(isSelected: Boolean = false): SalonDto {
    return SalonDto(
        id = id,
        name = name,
        logo = logo,
        cityId = cityId,
        provinceId = provinceId,
        isActive = isActive,
        isSelected = isSelected
    )
}

