package ir.sahrapanel.app.core.data.repository

import ir.sahrapanel.app.core.data.data_source.local.db.dao.LocationDao
import ir.sahrapanel.app.core.data.data_source.remote.LocationDataSource
import ir.sahrapanel.app.core.data.model.toDomain
import ir.sahrapanel.app.core.data.model.toEntity
import ir.sahrapanel.app.core.domain.model.City
import ir.sahrapanel.app.core.domain.model.LocationRepository
import ir.sahrapanel.app.core.domain.model.Province


class LocationRepositoryImpl(
    private val locationDao: LocationDao,
    private val apiService: LocationDataSource
) : LocationRepository {

    override suspend fun getProvinces(): Result<List<Province>> {
        return try {
            // 1. Check SQLite Cache First
            val cached = locationDao.getProvinces()
            if (cached.isNotEmpty()) {
                return Result.success(cached.map { it.toDomain() })
            }

            // 2. Fetch from Network if empty
            apiService.getProvinces().fold(
                onSuccess = { dtos ->
                    locationDao.insertProvinces(dtos.map { it.toEntity() })
                    val freshCached = locationDao.getProvinces().map { it.toDomain() }
                    Result.success(freshCached)
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCitiesForProvince(provinceId: Long): Result<List<City>> {
        return try {
            // 1. Check if we already have cities cached for this specific province
            val cachedCities = locationDao.getCitiesForProvince(provinceId)
            if (cachedCities.isNotEmpty()) {
                return Result.success(cachedCities.map { it.toDomain() })
            }

            // 2. Cache is empty -> Fetch from the server
            apiService.getCities(provinceId).fold(
                onSuccess = { dtos ->
                    // 3. Save all cities into Room database at once
                    locationDao.insertCities(dtos.map { it.toEntity() })

                    // 4. Query Room again so it filters safely for this specific province
                    val freshCachedCities =
                        locationDao.getCitiesForProvince(provinceId).map { it.toDomain() }
                    Result.success(freshCachedCities)
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}