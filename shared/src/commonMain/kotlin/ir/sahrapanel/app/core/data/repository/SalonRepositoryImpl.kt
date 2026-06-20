@file:OptIn(ExperimentalUuidApi::class)

package ir.sahrapanel.app.core.data.repository

import ir.sahrapanel.app.core.data.dataSource.local.db.dao.SalonDao
import ir.sahrapanel.app.core.data.dataSource.local.db.entity.SalonEntity
import ir.sahrapanel.app.core.data.dataSource.remote.SalonDataSource
import ir.sahrapanel.app.core.data.model.toDomain
import ir.sahrapanel.app.features.create_salon.data.CreateSalonRequest
import ir.sahrapanel.app.core.domain.model.Salon
import ir.sahrapanel.app.core.domain.repository.SalonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class SalonRepositoryImpl(
    private val salonDataSource: SalonDataSource,
    private val salonDao: SalonDao
) : SalonRepository {
    override suspend fun create(createSalonRequest: CreateSalonRequest): Result<Unit> {
        return salonDataSource.create(createSalonRequest).fold(
            onSuccess = {
                // You can optionally save to a local cache database here if needed!
                salonDao.insertSalon(
                    SalonEntity(
                        name = createSalonRequest.name,
                        logo = createSalonRequest.logo,
                        isActive = true,
                        provinceId = createSalonRequest.provinceId,
                        cityId = createSalonRequest.cityId,
                        createdAt = Clock.System.now(),
                        updatedAt = Clock.System.now()
                    )
                )
                Result.success(Unit)
            },
            onFailure = { exception ->
                // Passes the exact error (with your server's Persian message) up to the ViewModel
                Result.failure(exception)
            }
        )
    }

    override  fun get(salonId: Uuid): Flow<Salon> {
        return salonDao.getSalonById(salonId).mapNotNull { it?.toDomain() }
    }

    override fun getAll(): Flow<List<Salon>> {
        return salonDao.observeActiveSalons().map { list ->
                list.map { it.toDomain() }
            }
    }

}