package ir.sahrapanel.app.core.data.model

import ir.sahrapanel.app.core.data.data_source.local.db.entity.CityEntity
import ir.sahrapanel.app.core.data.data_source.local.db.entity.ProvinceEntity
import ir.sahrapanel.app.core.location.domain.City
import ir.sahrapanel.app.core.location.domain.Province

// --- Province Mappers ---

fun ProvinceEntity.toDomain() = Province(
    id = id,
    name = name,
    slug = slug,
    telPrefix = telPrefix
)

fun ProvinceDto.toEntity() = ProvinceEntity(
    id = id,
    name = name,
    slug = slug,
    telPrefix = telPrefix
)

fun ProvinceDto.toDomain() = Province(
    id = id,
    name = name,
    slug = slug,
    telPrefix = telPrefix
)

// --- City Mappers ---

fun CityEntity.toDomain() = City(
    id = id,
    name = name,
    slug = slug,
    provinceId = provinceId,
)

fun CityDto.toEntity() = CityEntity(
    id = id,
    name = name,
    slug = slug,
    provinceId = provinceId,
    countyId = null
)

fun CityDto.toDomain() = City(
    id = id,
    name = name,
    slug = slug,
    provinceId = provinceId,
)